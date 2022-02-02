package io.sh4.services

import io.sh4.models.ShopAddress
import io.sh4.models.ShopAddresses
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

fun getShopAddress(id: Int): ShopAddress? {
    val shopAddress = transaction {
        ShopAddresses.select { ShopAddresses.id eq id }.map { ShopAddresses.toShopAddress(it) }
    }
    return if (shopAddress.isEmpty()) null else shopAddress[0]
}

fun getAllShopAddresses(): List<ShopAddress> {
    val shopAddresses = transaction {
        ShopAddresses.selectAll().map { ShopAddresses.toShopAddress(it) }
    }
    return shopAddresses
}

fun addShopAddress(shopAddress: ShopAddress) {
//    if (!shopAddress.isValid()) return
    transaction {
        ShopAddresses.insert {
            it[lat] = shopAddress.lat
            it[lon] = shopAddress.lon
        }
    }
}

fun changeShopAddress(shopAddress: ShopAddress, id: Int) {
    transaction {
        ShopAddresses.update({ ShopAddresses.id eq id }) {
            it[lat] = shopAddress.lat
            it[lon] = shopAddress.lon
        }
    }
}

fun deleteShopAddress(id: Int) {
    transaction {
        ShopAddresses.deleteWhere { ShopAddresses.id eq id }
    }
}
