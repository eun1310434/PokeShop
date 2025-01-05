package com.euntaek.pokeshop.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.euntaek.pokeshop.core.database.dao.PokemonDao
import com.euntaek.pokeshop.core.database.model.PokemonEntity
import com.euntaek.pokeshop.core.database.util.TransactionProvider
import com.euntaek.pokeshop.core.network.model.NetworkPage
import com.euntaek.pokeshop.core.network.model.NetworkPokemon
import com.euntaek.pokeshop.core.network.service.PokemonService
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.TimeUnit


@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val pokemonDao: PokemonDao,
    private val pokemonService: PokemonService,
    private val transactionProvider: TransactionProvider
) : RemoteMediator<Int, PokemonEntity>() {
    private var currentPage = 0

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.HOURS.convert(1, TimeUnit.MILLISECONDS)
        return if (System.currentTimeMillis() - (pokemonDao.lastUpdated() ?: 0L) >= cacheTimeout) {
            // Cached data is up-to-date, so there is no need to re-fetch from network.
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            // Need to refresh cached data from network; returning LAUNCH_INITIAL_REFRESH here
            // will also block RemoteMediator's APPEND and PREPEND from running until REFRESH
            // succeeds.
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        Timber.e("loadType : " + loadType.name)
        return try {
            when (loadType) {
                LoadType.PREPEND -> MediatorResult.Success(endOfPaginationReached = true)
                LoadType.REFRESH -> {
                    currentPage = 0
                    Timber.e("currentPage : " + currentPage)
                    val result = refresh(pageSize = state.config.pageSize)
                    currentPage++
                    result
                }

                LoadType.APPEND -> {
                    Timber.e("currentPage : " + currentPage)
                    val result = load(
                        pageSize = state.config.pageSize,
                        offset = currentPage * state.config.pageSize
                    )
                    currentPage++
                    result
                }
            }
        } catch (e: IOException) {
            Timber.e("IOException : " + e.message)
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            Timber.e("HttpException : " + e.message)
            MediatorResult.Error(e)
        }
    }


    private suspend fun refresh(pageSize: Int): MediatorResult {
        val networkPagePokemons: NetworkPage<NetworkPokemon> =
            pokemonService.getPokemonPaginationList(
                limit = pageSize,
                offset = 0
            )

        transactionProvider.runAsTransaction {
            clearAndInsertAllToDB(networkItems = networkPagePokemons.results)
        }

        return MediatorResult.Success(endOfPaginationReached = networkPagePokemons.next == null)
    }

    private suspend fun load(pageSize: Int, offset: Int): MediatorResult {
        val networkPagePokemons = pokemonService.getPokemonPaginationList(
            limit = pageSize,
            offset = offset
        )

        transactionProvider.runAsTransaction {
            insertAllToDB(networkItems = networkPagePokemons.results)
        }

        return MediatorResult.Success(endOfPaginationReached = networkPagePokemons.next == null)
    }

    private suspend fun clearAndInsertAllToDB(networkItems: List<NetworkPokemon>) {
        pokemonDao.clearAll()
        insertAllToDB(networkItems = networkItems)
    }

    private suspend fun insertAllToDB(networkItems: List<NetworkPokemon>) {
        val entities = networkItems.map { networkPokemon ->
            PokemonEntity(
                id = networkPokemon.id,
                name = networkPokemon.name,
                url = networkPokemon.url,
                imageUrl = networkPokemon.imageUrl,
                gifUrl = networkPokemon.gifUrl
            )
        }
        pokemonDao.insertAll(entities)
    }
}