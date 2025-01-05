package com.euntaek.pokeshop.core.database.di

import com.euntaek.pokeshop.core.database.PokeShopDatabase
import com.euntaek.pokeshop.core.database.dao.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    fun providePokemonDao(
        database: PokeShopDatabase,
    ): PokemonDao = database.pokemonDao()
}
