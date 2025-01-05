package com.euntaek.pokeshop.core.network.di

import androidx.core.os.trace
import com.euntaek.pokeshop.core.network.BuildConfig
import com.euntaek.pokeshop.core.network.service.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideOkHttpCallFactory(): Call.Factory = trace("PokeShopOkHttpCallFactory") {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    },
            )
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        networkJson: Json,
        okhttpCallFactory: dagger.Lazy<Call.Factory>
    ): Retrofit = trace("PokeShopRetrofit") {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            // Use callFactory lambda here with dagger.Lazy<Call.Factory>
            // to prevent initializing OkHttp on the main thread.
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
    }


    @Provides
    @Singleton
    fun providePokemonService(retrofit: Retrofit): PokemonService {
        return retrofit.create(PokemonService::class.java)
    }
}