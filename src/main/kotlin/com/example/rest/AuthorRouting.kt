package com.example.rest

import com.example.entities.Author
import com.example.service.AuthorService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configuringAuthorRouting() {
    var authorService =  AuthorService()
    var endpoint = "/author"

    routing {
        get(endpoint){
            call.respond(authorService.findAll());
        }

        get("$endpoint/{id}"){
            var author = authorService.findById(call.parameters["id"]!!.toInt())
            call.respond(message = author)
        }

        post(endpoint){
            var author = call.receive<Author>()

        }
    }
}