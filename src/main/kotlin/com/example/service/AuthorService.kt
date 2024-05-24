package com.example.service

import com.example.entities.Author
import com.example.entities.Authors
import com.example.plugins.DatabaseConnection.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class AuthorService {

    suspend fun findAll(): List<Author> = dbQuery {
        Authors.selectAll().map { row ->
            Author(id = row[Authors.id], first_name = row[Authors.first_name], last_name = row[Authors.last_name])
        }
    }

    suspend fun findById(id: Int): Author = dbQuery {
        Authors.select{Authors.id eq id}.map { row ->
            Author(id = row[Authors.id], first_name = row[Authors.first_name], last_name = row[Authors.last_name])
        }.single()
    }

    suspend fun insert(author: Author): Unit{
        Authors.insert {
            it[Authors.id] = author.id
            it[Authors.first_name] = author.first_name
            it[Authors.last_name] = author.last_name
        }
    }

    suspend fun update(id: Int,author: Author): Unit{
        Authors.update({Authors.id eq  id}) {
            it[Authors.id] = author.id
            it[Authors.first_name] = author.first_name
            it[Authors.last_name] = author.last_name
        }
    }

    suspend fun delete(id: Int){
        Authors.deleteWhere { Authors.id eq id }
    }
}
