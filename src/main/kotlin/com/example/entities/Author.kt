package com.example.entities

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*


@Serializable
data class Author(val id: Int, val first_name: String, val last_name: String)

object Authors: Table(){
    val id = integer("id").autoIncrement()
    val first_name = varchar("first_name", 50)
    val last_name = varchar("las_name", 50)

    override val primaryKey = PrimaryKey(id)
}
