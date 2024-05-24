package com.example.plugins

import com.example.entities.Authors
import com.example.entities.Books
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import org.postgresql.Driver

object DatabaseConnection {
    fun init(){
        val driverClassName = "org.postgresql.Driver"
        val jdbcUrl = "jdbc:postgresql://localhost:5432/postgres"
        val database = Database.connect(jdbcUrl, driverClassName,"postgres", "postgres")
        transaction(database) {
            SchemaUtils.create(Authors)
            SchemaUtils.create(Books)
        }

    }
    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}
