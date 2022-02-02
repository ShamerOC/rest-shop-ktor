package io.sh4.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

data class Basket(
    val id : Int? = null,
    val item : Int,
    val userId : Int
)

object Baskets : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val item: Column<Int> = integer("item")
    val userId: Column<Int> = integer("userId")

    override val primaryKey = PrimaryKey(id, name="PK_User_ID")

    fun toBasket(row : ResultRow): Basket =
        Basket (
            id = row[id],
            item = row[item],
            userId = row[userId]
        )
}