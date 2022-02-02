package io.sh4.routes

import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.sh4.models.Address
import io.sh4.models.Auth
import io.sh4.models.User

fun Route.auth() {
    post("/login") {
        val user = call.receive<Auth>()
        call.respond(jsonOrJsonWithError(io.sh4.services.auth(user.name, user.password)))
    }
}

fun jsonOrJsonWithError(user: User?): String {
    var respose: String
    if (user != null) {
        respose = Gson().toJson(user)
    } else {
        respose = Gson().toJson("Error: Address not found")
    }
    return respose
}