package com.euntaek.pokeshop.core.data.repository

import androidx.paging.Pager
import com.euntaek.pokeshop.core.database.model.PokemonEntity

interface PokemonRepository {
    fun getPokemonPager(): Pager<Int, PokemonEntity>
}