package com.euntaek.pokeshop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.euntaek.pokeshop.feature.home.HomeRoute
import com.euntaek.pokeshop.feature.home.homeSection
import com.euntaek.pokeshop.feature.pokemondetails.navigateToPokemonDetails
import com.euntaek.pokeshop.feature.pokemondetails.pokemonDetailsSection

@Composable
fun PokeShopNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        homeSection(onPokemonClick = navController::navigateToPokemonDetails)
        pokemonDetailsSection()
    }
}