package com.euntaek.pokeshop.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.euntaek.pokeshop.core.database.model.PokemonEntity

@Dao
interface PokemonDao : BaseDao<PokemonEntity> {
    @Query("SELECT * FROM POKEMONS ORDER BY id ASC")
    fun pagingSource(): PagingSource<Int, PokemonEntity>

    @Query("DELETE FROM POKEMONS")
    suspend fun clearAll()

    @Query("SELECT MAX(last_update) FROM POKEMONS")
    suspend fun lastUpdated(): Long?

    @Query("SELECT MAX(id) FROM POKEMONS")
    suspend fun lastId(): Long?
}
