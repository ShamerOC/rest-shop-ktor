import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.sh4.models.Address
import io.sh4.models.Basket
import io.sh4.services.*


fun Route.basket() {
    get ("/baskets") {
        val baskets = getAllBaskets()
        call.respond(baskets)
    }

    get ("/baskets/{id}") {
        val id = call.parameters["id"]!!.toInt()
        val basket = getBasket(id)
        call.respond(jsonOrJsonWithError(basket))
    }

    post ("/baskets") {
        val basket = call.receive<Basket>()
        addBasket(basket)
        call.respond(jsonOrJsonWithError(basket))
    }

    put ("/baskets/{id}") {
        val id = call.parameters["id"]!!.toInt()
        val basket = call.receive<Basket>()
        changeBasket(basket, id)
        call.respond(jsonOrJsonWithError(basket))
    }

    delete ("/baskets/{id}") {
        val id = call.parameters["id"]!!.toInt()
        deleteBasket(id)
        call.respond(id)
    }

    delete ("/baskets/{id}") {
        val id = call.parameters["id"]!!.toInt()
        deleteBasket(id)
        call.respond(id)
    }
}

fun jsonOrJsonWithError(basket: Basket?): String {
    var respose: String
    if (basket != null) {
        respose = Gson().toJson(basket)
    } else {
        respose = Gson().toJson("Error: Basket not found")
    }
    return respose
}