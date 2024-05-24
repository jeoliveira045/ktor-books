package com.example.entities

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*


@Serializable
data class Book(val id: Int, val book_name: String, val author_id: Int)

object Books: Table(){
    val id = integer("id").autoIncrement()
    val book_name = varchar("book_name", 50)
    val author_id = integer("author_id").references(Authors.id)

    override val primaryKey = PrimaryKey(id)

}
