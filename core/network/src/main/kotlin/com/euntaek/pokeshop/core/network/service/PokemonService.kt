package com.euntaek.pokeshop.core.network.service

import com.euntaek.pokeshop.core.network.model.NetworkPage
import com.euntaek.pokeshop.core.network.model.NetworkPokemon
import com.euntaek.pokeshop.core.network.model.NetworkPokemonSpec
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    suspend fun getPokemonPaginationList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    ): NetworkPage<NetworkPokemon>

    @GET("pokemon/{name}")
    suspend fun getPokemonSpec(@Path("name") name: String): NetworkPokemonSpec
}