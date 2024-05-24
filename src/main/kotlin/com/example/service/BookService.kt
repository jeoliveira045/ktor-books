package com.example.service

import com.example.entities.Book
import com.example.entities.Books
import com.example.plugins.DatabaseConnection
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class BookService {
    suspend fun findAll(): List<Book> = DatabaseConnection.dbQuery {
        Books.selectAll().map { row ->
            Book(id = row[Books.id], book_name = row[Books.book_name], author_id = row[Books.author_id])
        }
    }

    suspend fun findById(id: Int): Book = DatabaseConnection.dbQuery {
        Books.select { Books.id eq id }.map { row ->
            Book(id = row[Books.id], book_name = row[Books.book_name], author_id = row[Books.author_id])
        }.single()
    }

    suspend fun insert(Book: Book): Unit{
        Books.insert {
            it[Books.id] = Book.id
            it[Books.book_name] = Book.book_name
            it[Books.author_id] = Book.author_id
        }
    }

    suspend fun update(id: Int,Book: Book): Unit{
        Books.update({Books.id eq  id}) {
            it[Books.id] = Book.id
            it[Books.book_name] = Book.book_name
            it[Books.author_id] = Book.author_id
        }
    }

    suspend fun delete(id: Int){
        Books.deleteWhere { Books.id eq id }
    }
}