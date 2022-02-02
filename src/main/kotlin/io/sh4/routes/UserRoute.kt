import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.sh4.models.User
import io.sh4.services.*


fun Route.user() {
    get ("/users") {
        val users = getAllUsers()
        call.respond(users)
    }

    get ("/users/{id}") {
        val id = call.parameters["id"]!!.toInt()
        val user = getUser(id)
        call.respond(jsonOrJsonWithError(user))
    }

    post ("/users") {
        val user = call.receive<User>()
        addUser(user)
        val response = getUserByName(user.name)
        call.respond(jsonOrJsonWithError(response))
    }

    post ("/users/auth") {
        val user = call.receive<User>()
        call.respond(authUser(user))
    }

    put ("/users/{id}") {
        val id = call.parameters["id"]!!.toInt()
        val user = call.receive<User>()
        changeUser(user, id)
        call.respond(jsonOrJsonWithError(user))
    }

    put ("/users/{id}") {
        val id = call.parameters["id"]!!.toInt()
        val user = call.receive<User>()
        changeUser(user, id)
        call.respond(jsonOrJsonWithError(user))
    }

    delete ("/users/{id}") {
        val id = call.parameters["id"]!!.toInt()
        deleteUser(id)
        call.respond(id)
    }
}

fun jsonOrJsonWithError(user: User?): String {
    var respose: String
    if (user != null) {
        respose = Gson().toJson(user)
    } else {
        respose = Gson().toJson("Error: User not found")
    }
    return respose
}