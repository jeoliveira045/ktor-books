package com.example.rest

import com.example.entities.Book
import com.example.service.BookService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configuringBookRouting(){
    var service =  BookService()
    var endpoint = "/book"

    routing {
        get(endpoint){
            call.respond(service.findAll());
        }

        get("$endpoint/{id}"){
            var book = service.findById(call.parameters["id"]!!.toInt())
            call.respond(message = book)
        }

        post(endpoint){
            var book = call.receive<Book>()
            service.insert(book)
        }

        put("$endpoint/{id}"){
            var book = call.receive<Book>()
            service.update(call.parameters["id"]!!.toInt(), book)
        }

        delete("$endpoint/{id}"){
            service.delete(call.parameters["id"]!!.toInt())
        }

    }
}