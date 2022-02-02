package io.sh4.models

import io.sh4.models.Addresses.autoIncrement
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

data class ShopAddress(
    val id : Int? = null,
    val lat : Double,
    val lon : Double
)

object ShopAddresses : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val lat: Column<Double> = double("lat")
    val lon: Column<Double> = double("lon")

    override val primaryKey = PrimaryKey(id, name="PK_Address_ID")

    fun toShopAddress(row : ResultRow): ShopAddress =
        ShopAddress (
            id = row[id],
            lat = row[lat],
            lon = row[lon]
        )
}