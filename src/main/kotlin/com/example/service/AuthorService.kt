package com.example.service

import com.example.entities.Author
import com.example.entities.Authors
import com.example.plugins.DatabaseConnection.dbQuery
import org.jetbrains.exposed.sql.selectAll

class AuthorService {

    suspend fun findAll(): List<Author> = dbQuery {
        Authors.selectAll().map { row ->
            Author(id = row[Authors.id], first_name = row[Authors.first_name], last_name = row[Authors.last_name])
        }
    }
}
