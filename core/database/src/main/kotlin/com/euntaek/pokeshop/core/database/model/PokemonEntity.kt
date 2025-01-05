package com.euntaek.pokeshop.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.euntaek.pokeshop.core.model.Pokemon

@Entity(tableName = "pokemons")
data class PokemonEntity(
    val id: Int,
    @PrimaryKey val name: String,
    val url: String,
    val imageUrl: String,
    val gifUrl: String,
    @ColumnInfo(name = "last_update") val lastUpdate: Long = System.currentTimeMillis(),
)

fun PokemonEntity.asPokemon() = Pokemon(
    id = this.id,
    name = this.name,
    url = this.url,
    imageUrl = this.imageUrl,
    gifUrl = this.gifUrl
)
