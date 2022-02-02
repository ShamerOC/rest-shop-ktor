package io.sh4.routes

import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.sh4.models.ShopAddress
import io.sh4.services.*

fun Route.shopAddress() {
    get ("/shopadresses") {
        val shopadresses = getAllShopAddresses()
        call.respond(shopadresses)
    }

    get ("/shopadresses/{id}") {
        val id = call.parameters["id"]!!.toInt()
        val shopAddress = getShopAddress(id)
        call.respond(jsonOrJsonWithError(shopAddress))
    }

    post ("/shopadresses") {
        val shopAddress = call.receive<ShopAddress>()
        addShopAddress(shopAddress)
        call.respond(jsonOrJsonWithError(shopAddress))
    }

    put ("/shopadresses/{id}") {
        val id = call.parameters["id"]!!.toInt()
        val shopAddress = call.receive<ShopAddress>()
        changeShopAddress(shopAddress, id)
        call.respond(jsonOrJsonWithError(shopAddress))
    }

    delete ("/shopadresses/{id}") {
        val id = call.parameters["id"]!!.toInt()
        deleteShopAddress(id)
        call.respond(id)
    }
}

fun jsonOrJsonWithError(shopAddress: ShopAddress?): String {
    var respose: String
    if (shopAddress != null) {
        respose = Gson().toJson(shopAddress)
    } else {
        respose = Gson().toJson("Error: shopAddress not found")
    }
    return respose
}