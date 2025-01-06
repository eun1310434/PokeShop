package com.euntaek.pokeshop.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import androidx.palette.graphics.Palette
import com.euntaek.pokeshop.core.designsystem.component.CachedAsyncImage
import com.euntaek.pokeshop.core.designsystem.component.PokeShopText
import com.euntaek.pokeshop.core.designsystem.theme.toBrush
import com.euntaek.pokeshop.core.designsystem.util.isLandScape
import com.euntaek.pokeshop.core.model.Pokemon

@Composable
internal fun HomeScreen(
    onPokemonClick: (Pokemon) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val pokemonList = homeViewModel.pokemonPagingList.collectAsLazyPagingItems()
    HomeContent(pokemons = pokemonList, onPokemonClick = onPokemonClick)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeContent(
    pokemons: LazyPagingItems<Pokemon>,
    onPokemonClick: (Pokemon) -> Unit
) {
    LaunchedEffect(key1 = pokemons.loadState) {
        if (pokemons.loadState.refresh is LoadState.Error) {
            //TODO "Error: " + (pokemons.loadState.refresh as LoadState.Error).error.message,
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        PullToRefreshBox(
            isRefreshing = pokemons.loadState.refresh is LoadState.Loading,
            onRefresh = { pokemons.refresh() },
        ) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                columns = GridCells.Fixed(count = if (isLandScape()) 5 else 3),
                contentPadding = PaddingValues(10.dp),
            ) {
                items(
                    count = pokemons.itemCount,
                    key = pokemons.itemKey { it.id }
                ) { index ->
                    val pokemon = pokemons[index]
                    if (pokemon != null) {
                        PokemonItem(pokemon = pokemon) {
                            onPokemonClick(pokemon)
                        }
                    }
                }
                item {
                    if (pokemons.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.padding(20.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun PokemonItem(pokemon: Pokemon, onClick: () -> Unit) {
    var palette: Palette? by remember { mutableStateOf(null) }
    PokemonItemLayout(palette = palette, onClick = onClick) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CachedAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                placeholderDrawableResId = com.euntaek.pokeshop.core.designsystem.R.drawable.pokeball,
                imageURL = pokemon.imageUrl,
                onPalette = { palette = it }
            )
            if (palette != null) {
                Spacer(modifier = Modifier.height(5.dp))
                PokeShopText(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    text = pokemon.name,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun PokemonItemLayout(
    palette: Palette?,
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    Card(
        modifier = Modifier
            .aspectRatio(0.75f),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = onClick)
                .background(color = Color.White)
        ) {
            if (palette != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .align(Alignment.TopStart)
                        .background(brush = palette.toBrush(defaultColor = Color.White))
                )
            }
            content()
        }
    }
}
