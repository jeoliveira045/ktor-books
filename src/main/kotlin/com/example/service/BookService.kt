package com.example.service

import com.example.entities.Author
import com.example.entities.Authors
import com.example.entities.Book
import com.example.entities.Books
import com.example.plugins.DatabaseConnection
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class BookService {
    suspend fun findAll(): List<Book> = DatabaseConnection.dbQuery {
        Books.selectAll().map { row ->
            Book(id = row[Books.id], book_name = row[Books.book_name], author_id = row[Books.author_id])
        }
    }

    suspend fun findById(id: Int): Book? = DatabaseConnection.dbQuery {
        Books.select{Books.id eq id}.map { row ->
            Book(id = row[Books.id], book_name = row[Books.book_name], author_id = row[Books.author_id])
        }.singleOrNull()
    }
}