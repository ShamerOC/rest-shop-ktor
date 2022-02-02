package io.sh4

import io.ktor.application.*
import io.sh4.plugins.*
import org.jetbrains.exposed.*
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    Database.connect("jdbc:sqlite:./identifier.sqlite", "org.sqlite.JDBC")
    configureRouting()
    configureSerialization()
    configureDatabase()
}
