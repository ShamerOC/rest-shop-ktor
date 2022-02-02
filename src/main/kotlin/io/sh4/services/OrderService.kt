package io.sh4.services

import com.google.gson.Gson
import io.sh4.models.Order
import io.sh4.models.Orders
import io.sh4.models.isValid
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun getOrder(id: Int): Order? {
    val order = transaction {
        Orders.select { Orders.id eq id }.map { Orders.toOrder(it) }
    }
    return if (order.isEmpty()) null else order[0]
}

fun getAllOrders(): List<Order> {
    val orders = transaction {
        Orders.selectAll().map { Orders.toOrder(it) }
    }
    return orders
}

fun addOrder(order: Order) {
    transaction {
        Orders.insert {
            it[Orders.UID] = UID
            it[Orders.amount] = amount
            it[Orders.productId] = productId
        }
    }
}

fun changeOrder(order: Order, id: Int) {
    transaction {
        Orders.update({ Orders.id eq id }) {
            it[Orders.UID] = UID
            it[Orders.amount] = amount
            it[Orders.productId] = productId
        }
    }
}

fun deleteOrder(id: Int) {
    transaction {
        Orders.deleteWhere { Orders.id eq id }
    }
}
