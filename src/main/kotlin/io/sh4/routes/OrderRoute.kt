import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.sh4.models.Address
import io.sh4.models.Basket
import io.sh4.models.Order
import io.sh4.services.*


fun Route.order() {
    get ("/orders") {
        val orders = getAllOrders()
        call.respond(orders)
    }

    get ("/orders/{id}") {
        val id = call.parameters["id"]!!.toInt()
        val order = getOrder(id)
        call.respond(jsonOrJsonWithError(order))
    }

    post ("/orders") {
        val order = call.receive<Order>()
        addOrder(order)
        call.respond(jsonOrJsonWithError(order))
    }

    put ("/orders/{id}") {
        val id = call.parameters["id"]!!.toInt()
        val order = call.receive<Order>()
        changeOrder(order, id)
        call.respond(jsonOrJsonWithError(order))
    }

    delete ("/orders/{id}") {
        val id = call.parameters["id"]!!.toInt()
        deleteOrder(id)
        call.respond(id)
    }
}

fun jsonOrJsonWithError(order: Order?): String {
    var respose: String
    if (order != null) {
        respose = Gson().toJson(order)
    } else {
        respose = Gson().toJson("Error: Order not found")
    }
    return respose
}