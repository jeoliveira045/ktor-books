package com.example.rest

import com.example.entities.Author
import com.example.service.AuthorService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configuringAuthorRouting() {
    var service =  AuthorService()
    var endpoint = "/author"

    routing {
        get(endpoint){
            call.respond(service.findAll());
        }

        get("$endpoint/{id}"){
            var author = service.findById(call.parameters["id"]!!.toInt())
            call.respond(message = author)
        }

        post(endpoint){
            var author = call.receive<Author>()
            service.insert(author)
        }

        put("$endpoint/{id}"){
            var author = call.receive<Author>()
            service.update(call.parameters["id"]!!.toInt(), author)
        }

        delete("$endpoint/{id}"){
            service.delete(call.parameters["id"]!!.toInt())
        }

    }
}