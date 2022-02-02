package io.sh4.services

import com.google.gson.Gson
import io.sh4.models.Basket
import io.sh4.models.Baskets
import io.sh4.models.isValid
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun getBasket(id: Int): Basket? {
    val basket = transaction {
        Baskets.select { Baskets.id eq id }.map { Baskets.toBasket(it) }
    }
    return if (basket.isEmpty()) null else basket[0]
}

fun getAllBaskets(): List<Basket?> {
    val baskets = transaction {
        Baskets.selectAll().map { Baskets.toBasket(it) }
    }
    return baskets
}

fun addBasket(basket: Basket) {
    transaction {
        Baskets.insert {
            it[Baskets.item] = item
            it[Baskets.userId] = userId
        }
    }
}

fun changeBasket(basket: Basket, id: Int) {
    transaction {
        Baskets.update({ Baskets.id eq id }) {
            it[Baskets.item] = item
            it[Baskets.userId] = userId
        }
    }
}

fun deleteBasket(id: Int) {
    transaction {
        Baskets.deleteWhere { Baskets.id eq id }
    }
}
