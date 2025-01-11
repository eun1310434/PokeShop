package com.euntaek.pokeshop.feature.home

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.euntaek.pokeshop.core.model.Pokemon
import kotlinx.serialization.Serializable


@Serializable
data object HomeRoute

context(SharedTransitionScope)
fun NavGraphBuilder.homeSection(
    onPokemonClick: (Pokemon) -> Unit
) {
    composable<HomeRoute> {
        HomeScreen(animatedVisibilityScope = this@composable, onPokemonClick = onPokemonClick)
    }
}