package io.sh4.models

import io.sh4.models.Users.autoIncrement
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

data class Address(
    val id : Int? = null,
    val userId : Int,
    val address : String
)

object Addresses : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val userId: Column<Int> = integer("userId")
    val address: Column<String> = text("address")

    override val primaryKey = PrimaryKey(id, name="PK_Address_ID")

    fun toAddress(row : ResultRow): Address =
        Address (
            id = row[id],
            userId = row[userId],
            address = row[address]
        )
}