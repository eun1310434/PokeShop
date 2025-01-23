package com.euntaek.pokeshop.feature.pokemondetails

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import com.euntaek.pokeshop.core.model.Pokemon
import com.euntaek.pokeshop.core.navigation.serializableType
import kotlinx.serialization.Serializable
import kotlin.reflect.KType
import kotlin.reflect.typeOf

@Serializable
data class PokemonDetailsRoute(val pokemon: Pokemon) {
    companion object {
        val typeMap: Map<KType, NavType<Pokemon>> =
            mapOf(typeOf<Pokemon>() to serializableType<Pokemon>())
    }
}

fun NavController.navigateToPokemonDetails(pokemon: Pokemon) {
    navigate(route = PokemonDetailsRoute(pokemon = pokemon))
}

context(SharedTransitionScope)
fun NavGraphBuilder.pokemonDetailsSection() {
    composable<PokemonDetailsRoute>(typeMap = PokemonDetailsRoute.typeMap) {
        PokemonDetailsScreen(animatedVisibilityScope = this@composable)
    }
}