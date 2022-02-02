import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.sh4.models.Product
import io.sh4.services.*


fun Route.product() {
    get ("/products") {
        val products = getAllProducts()
        call.respond(products)
    }

    get ("/products/{id}") {
        val id = call.parameters["id"]!!.toInt()
        val product = getProduct(id)
        call.respond(jsonOrJsonWithError(product))
    }

    post ("/products") {
        val product = call.receive<Product>()
        addProduct(product)
        call.respond(jsonOrJsonWithError(product))
    }

    put ("/products/{id}") {
        val id = call.parameters["id"]!!.toInt()
        val product = call.receive<Product>()
        changeProduct(product, id)
        call.respond(jsonOrJsonWithError(product))
    }

    delete ("/products/{id}") {
        val id = call.parameters["id"]!!.toInt()
        deleteProduct(id)
        call.respond(id)
    }
}

fun jsonOrJsonWithError(product: Product?): String {
    val respose: String
    if (product != null) {
        respose = Gson().toJson(product)
    } else {
        respose = Gson().toJson("Error: Product not found")
    }
    return respose
}