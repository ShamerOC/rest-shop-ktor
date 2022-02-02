package io.sh4.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

data class Order(
    val id : Int? = null,
    val UID : Int,
    val amount : Double,
    val productId : Int
)

object Orders : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val UID: Column<Int> = integer("uid")
    val amount: Column<Double> = double("amount")
    val productId: Column<Int> = integer("productId")

    override val primaryKey = PrimaryKey(id, name="PK_Order_ID")

    fun toOrder(row : ResultRow): Order =
        Order (
            id = row[id],
            UID = row[UID],
            amount = row[amount],
            productId = row[productId]
        )

}
