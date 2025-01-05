package com.euntaek.pokeshop.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.euntaek.pokeshop.core.database.dao.PokemonDao
import com.euntaek.pokeshop.core.database.model.PokemonEntity
import com.euntaek.pokeshop.core.database.util.TransactionProvider
import com.euntaek.pokeshop.core.network.service.PokemonService
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val pokemonService: PokemonService,
    private val transactionProvider: TransactionProvider
) : PokemonRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPokemonPager(): Pager<Int, PokemonEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = 30
            ),
            remoteMediator = PokemonRemoteMediator(
                pokemonDao = pokemonDao,
                pokemonService = pokemonService,
                transactionProvider = transactionProvider
            ),
            pagingSourceFactory = {
                pokemonDao.pagingSource()
            }
        )
    }
}

