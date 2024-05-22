package com.example.plugins

import com.example.service.AuthorService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/authors"){
            var service = AuthorService()
            call.respond(service.findAll())
        }
    }
}
