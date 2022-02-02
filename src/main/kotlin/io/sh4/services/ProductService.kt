package io.sh4.services

import com.google.gson.Gson
import io.sh4.models.Product
import io.sh4.models.Products
import io.sh4.models.isValid
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun getProduct(id: Int): Product? {
    val product = transaction {
        Products.select { Products.id eq id }.map { Products.toProduct(it) }
    }
    return if (product.isEmpty()) null else product[0]
}

fun getAllProducts(): List<Product> {
    val products = transaction {
        Products.selectAll().map { Products.toProduct(it) }
    }
    return products
}

fun addProduct(product: Product) {
    if (!product.isValid()) return
    transaction {
        Products.insert {
            it[Products.name] = product.name
            it[Products.qty] = product.qty
            it[Products.price] = product.price
            it[Products.bio] = product.bio
        }
    }
}

fun changeProduct(product: Product, id: Int) {
    transaction {
        Products.update({ Products.id eq id }) {
            it[Products.name] = product.name
            it[Products.qty] = product.qty
            it[Products.price] = product.price
            it[Products.bio] = product.bio
        }
    }
}

fun deleteProduct(id: Int) {
    transaction {
        Products.deleteWhere { Products.id eq id }
    }
}
