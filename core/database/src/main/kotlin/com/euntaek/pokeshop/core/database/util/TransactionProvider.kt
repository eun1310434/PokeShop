package com.euntaek.pokeshop.core.database.util

import androidx.room.withTransaction
import com.euntaek.pokeshop.core.database.PokeShopDatabase
import javax.inject.Inject

class TransactionProvider @Inject constructor(
    private val database: PokeShopDatabase
) {
    suspend fun <T> runAsTransaction(block: suspend () -> T): T {
        return database.withTransaction(block)
    }
}