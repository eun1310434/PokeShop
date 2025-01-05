package com.euntaek.pokeshop.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.euntaek.pokeshop.core.database.dao.PokemonDao
import com.euntaek.pokeshop.core.database.model.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = true
)
abstract class PokeShopDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
