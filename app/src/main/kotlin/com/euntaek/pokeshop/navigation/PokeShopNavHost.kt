package com.euntaek.pokeshop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.euntaek.pokeshop.feature.home.HomeRoute
import com.euntaek.pokeshop.feature.home.homeSection

@Composable
fun PokeShopNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        homeSection(onPokemonClick = {})
    }
}