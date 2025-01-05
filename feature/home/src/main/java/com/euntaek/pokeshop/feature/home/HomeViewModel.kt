package com.euntaek.pokeshop.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.euntaek.pokeshop.core.data.repository.PokemonRepository
import com.euntaek.pokeshop.core.database.model.asPokemon
import com.euntaek.pokeshop.core.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    pokemonRepository: PokemonRepository
) : ViewModel() {
    val pokemonPagingList: Flow<PagingData<Pokemon>> = pokemonRepository
        .getPokemonPager().flow.map { pagingData ->
            pagingData.map { it.asPokemon() }
        }.cachedIn(viewModelScope)
}