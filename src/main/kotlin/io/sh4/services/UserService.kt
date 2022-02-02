package io.sh4.services

import com.google.gson.Gson
import io.sh4.models.User
import io.sh4.models.Users
import io.sh4.models.Users.password
import io.sh4.models.isValid
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun getUser(id: Int): User? {
    val user = transaction {
        Users.select { Users.id eq id }.map { Users.toUser(it) }
    }
    return if (user.isEmpty()) null else user[0]
}

fun getAllUsers(): List<User> {
    val users = transaction {
        Users.selectAll().map { Users.toUser(it) }
    }
    return users
}

fun getUserByName(name : String) : User? {
    val user = transaction {
        Users.select { Users.name eq name }.map { Users.toUser(it) }
    }
    return if (user.isEmpty()) null else user[0]
}

fun addUser(user: User) {
    if (!user.isValid()) return
    transaction {
        Users.insert {
            it[Users.name] = user.name
            it[Users.password] = user.password
        }
    }
}

fun changeUser(user: User, id: Int) {
    transaction {
        Users.update({ Users.id eq id }) {
            it[name] = user.name
            it[password] = password
        }
    }
}

fun deleteUser(id: Int) {
    transaction {
        Users.deleteWhere { Users.id eq id }
    }
}

fun authUser(user : User) : Boolean {
    var isUser = false
    transaction {
        val originalUser = Users.select { Users.name eq user.name }.single()
        isUser = originalUser[password] == user.password
    }
    return isUser
}


