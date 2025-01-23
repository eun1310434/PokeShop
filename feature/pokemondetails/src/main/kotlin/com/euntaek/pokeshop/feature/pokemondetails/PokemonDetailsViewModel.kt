package com.euntaek.pokeshop.feature.pokemondetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.euntaek.pokeshop.core.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val pokemon: Pokemon = savedStateHandle
        .toRoute<PokemonDetailsRoute>(typeMap = PokemonDetailsRoute.typeMap).pokemon
}