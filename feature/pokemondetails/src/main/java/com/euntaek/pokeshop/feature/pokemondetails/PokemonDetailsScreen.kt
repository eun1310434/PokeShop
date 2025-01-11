package com.euntaek.pokeshop.feature.pokemondetails

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.palette.graphics.Palette
import com.euntaek.pokeshop.core.designsystem.component.CachedAsyncImage
import com.euntaek.pokeshop.core.designsystem.component.PokeShopText
import com.euntaek.pokeshop.core.designsystem.component.PokemonSharedElementType
import com.euntaek.pokeshop.core.designsystem.component.pokemonSharedBounds
import com.euntaek.pokeshop.core.designsystem.theme.addOpacity
import com.euntaek.pokeshop.core.designsystem.theme.getDominantColor
import com.euntaek.pokeshop.core.model.Pokemon

@Composable
internal fun SharedTransitionScope.PokemonDetailsScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemonDetailsViewModel: PokemonDetailsViewModel = hiltViewModel()
) {
    PokemonDetails(
        animatedVisibilityScope = animatedVisibilityScope,
        pokemon = pokemonDetailsViewModel.pokemon
    )
}


@Composable
private fun SharedTransitionScope.PokemonDetails(
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemon: Pokemon
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PokeShopText(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .pokemonSharedBounds(
                    pokemonId = pokemon.id,
                    type = PokemonSharedElementType.NAME,
                    animatedVisibilityScope = animatedVisibilityScope,
                    enabled = LocalInspectionMode.current
                ),
            text = pokemon.name,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MaterialTheme.typography.displaySmall.merge(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(20.dp))
        PokemonCard(
            animatedVisibilityScope = animatedVisibilityScope,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio(0.75f),
            pokemon = pokemon
        )
    }
}

@Composable
private fun SharedTransitionScope.PokemonCard(
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier,
    pokemon: Pokemon
) {
    var palette: Palette? by remember { mutableStateOf(null) }
    PokemonCardLayout(
        modifier = modifier,
        backgroundColor = palette.getDominantColor().addOpacity()
    ) {
        CachedAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.75f)
                .pokemonSharedBounds(
                    pokemonId = pokemon.id,
                    type = PokemonSharedElementType.IMAGE,
                    animatedVisibilityScope = animatedVisibilityScope,
                    enabled = LocalInspectionMode.current
                ),
            placeholderDrawableResId = com.euntaek.pokeshop.core.designsystem.R.drawable.pokeball,
            imageURL = pokemon.gifUrl,
            onPalette = { palette = it }
        )
    }
}

@Composable
private fun PokemonCardLayout(
    modifier: Modifier,
    backgroundColor: Color,
    content: @Composable BoxScope.() -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(30.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
    ) {
        Box(
            modifier = Modifier
                .background(color = backgroundColor)
                .fillMaxSize()
                .padding(50.dp),
            contentAlignment = Alignment.Center,
            content = content
        )
    }
}