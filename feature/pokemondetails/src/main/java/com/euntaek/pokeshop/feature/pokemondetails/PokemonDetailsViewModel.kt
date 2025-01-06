package com.euntaek.pokeshop.feature.pokemondetails

import androidx.lifecycle.ViewModel
import com.euntaek.pokeshop.core.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    pokemonRepository: PokemonRepository
) : ViewModel() {

}