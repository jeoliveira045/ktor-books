package com.example.plugins

import com.example.entities.Authors
import com.example.entities.Books
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseConnection {
    fun init(){
        val driverClassName = "oracle.jdbc.OracleDriver"
        val jdbcUrl = "jdbc:oracle:thin:@localhost:1521/ORCLPDB1"
        val database = Database.connect(jdbcUrl, driverClassName,"system", "oracle")
        transaction(database) {
            SchemaUtils.create(Authors)
            SchemaUtils.create(Books)
        }

    }
    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}
