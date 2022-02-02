package io.sh4.services

import com.google.gson.Gson
import io.sh4.models.Address
import io.sh4.models.Addresses
import io.sh4.models.isValid
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun getAddress(id: Int): Address? {
    val address = transaction {
        Addresses.select { Addresses.id eq id }.map { Addresses.toAddress(it) }
    }
    return if (address.isEmpty()) null else address[0]
}

fun getAllAddresses(): List<Address> {
    val addresss = transaction {
        Addresses.selectAll().map { Addresses.toAddress(it) }
    }
    return addresss
}

fun addAddress(address: Address) {
    transaction {
        Addresses.insert {
            it[Addresses.userId] = userId
            it[Addresses.address] = address.address
        }
    }
}

fun changeAddress(address: Address, id: Int) {
    transaction {
        Addresses.update({ Addresses.id eq id }) {
            it[Addresses.userId] = userId
            it[Addresses.address] = address.address
        }
    }
}

fun deleteAddress(id: Int) {
    transaction {
        Addresses.deleteWhere { Addresses.id eq id }
    }
}
