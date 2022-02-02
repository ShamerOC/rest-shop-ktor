package io.sh4.plugins

import io.ktor.routing.*
import io.ktor.application.*
import product
import address
import order
import user
import basket
import io.ktor.response.*
import io.sh4.routes.auth
import io.sh4.routes.shopAddress

fun Application.configureRouting() {

    routing {
        product()
        address()
        order()
        user()
        basket()
        shopAddress()
        auth()
        get("/") {
            call.respond("")
        }
    }
}
