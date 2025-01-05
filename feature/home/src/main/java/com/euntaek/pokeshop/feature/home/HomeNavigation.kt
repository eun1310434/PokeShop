package com.euntaek.pokeshop.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.euntaek.pokeshop.core.model.Pokemon
import kotlinx.serialization.Serializable


@Serializable
data object HomeRoute

fun NavGraphBuilder.homeSection(
    onPokemonClick: (Pokemon) -> Unit
) {
    composable<HomeRoute> {
        HomeScreen(onPokemonClick = onPokemonClick)
    }
}