package io.sh4.services

import io.sh4.models.User
import io.sh4.models.Users
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun auth(login : String, password : String) : User? {
    val users = transaction {
        Users.select { Users.name eq login }.map { Users.toUser(it) }
    }
    users.forEach { user ->
        if (user.password == password) return user
    }
    return null
}