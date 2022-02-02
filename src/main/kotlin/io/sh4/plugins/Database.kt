package io.sh4.plugins

import io.ktor.application.*
import io.sh4.models.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabase() {
    transaction {
        SchemaUtils.create(Products)
        SchemaUtils.create(Baskets)
        SchemaUtils.create(Orders)
        SchemaUtils.create(Users)
        SchemaUtils.create(Addresses)
        SchemaUtils.create(ShopAddresses)

        Products.insert {
            it[Products.name] = "Bitcoin"
            it[Products.qty] = 100
            it[Products.price] = 50813.94
            it[Products.bio] = "First cryptocurrency"
        }

        Products.insert {
            it[Products.name] = "Ethereum"
            it[Products.qty] = 100
            it[Products.price] = 4421.40
            it[Products.bio] = "Biggest blockchain"
        }

        Products.insert {
            it[Products.name] = "Binance Coin"
            it[Products.qty] = 100
            it[Products.price] = 600.47
            it[Products.bio] = "Cryptocurrency created by the biggest exchange"
        }

        Products.insert {
            it[Products.name] = "Tether"
            it[Products.qty] = 100
            it[Products.price] = 1.0
            it[Products.bio] = "Most popular stablecoin"
        }

    }
}