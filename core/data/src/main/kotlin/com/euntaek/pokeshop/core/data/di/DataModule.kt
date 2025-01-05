package com.euntaek.pokeshop.core.data.di

import com.euntaek.pokeshop.core.data.repository.PokemonRepository
import com.euntaek.pokeshop.core.data.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {
    @Binds
    fun bindsPokemonRepository(pokemonRepositoryImpl: PokemonRepositoryImpl): PokemonRepository
}
