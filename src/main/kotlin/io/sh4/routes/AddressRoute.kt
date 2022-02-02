import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.sh4.models.Address
import io.sh4.services.*


fun Route.address() {
    get ("/addresses") {
        val addresses = getAllAddresses()
        call.respond(addresses)
    }

    get ("/addresses/{id}") {
        val id = call.parameters["id"]!!.toInt()
        val address = getAddress(id)
        call.respond(jsonOrJsonWithError(address))
    }

    post ("/addresses") {
        val address = call.receive<Address>()
        addAddress(address)
        call.respond(jsonOrJsonWithError(address))
    }

    put ("/addresses/{id}") {
        val id = call.parameters["id"]!!.toInt()
        val address = call.receive<Address>()
        changeAddress(address, id)
        call.respond(jsonOrJsonWithError(address))
    }

    delete ("/addresses/{id}") {
        val id = call.parameters["id"]!!.toInt()
        deleteAddress(id)
        call.respond(id)
    }
}

fun jsonOrJsonWithError(address: Address?): String {
    var respose: String
    if (address != null) {
        respose = Gson().toJson(address)
    } else {
        respose = Gson().toJson("Error: Address not found")
    }
    return respose
}