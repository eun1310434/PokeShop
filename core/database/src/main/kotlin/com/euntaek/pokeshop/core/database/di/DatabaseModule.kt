package com.euntaek.pokeshop.core.database.di

import android.content.Context
import androidx.room.Room
import com.euntaek.pokeshop.core.database.PokeShopDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesPokeShopDatabase(
        @ApplicationContext context: Context,
    ): PokeShopDatabase = Room.databaseBuilder(
        context = context,
        klass = PokeShopDatabase::class.java,
        name = "poke-shop-database",
    ).fallbackToDestructiveMigration().build()
}