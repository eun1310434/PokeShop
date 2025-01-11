package com.euntaek.pokeshop.core.designsystem.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

context(SharedTransitionScope)
@Composable
fun Modifier.pokemonSharedBounds(
    pokemonId: Int,
    type: PokemonSharedElementType,
    animatedVisibilityScope: AnimatedVisibilityScope,
    enabled: Boolean
): Modifier {
    return if (enabled) {
        this
    } else {
        this.sharedBounds(
            sharedContentState = rememberSharedContentState(key = type.key + "/${pokemonId}"),
            animatedVisibilityScope = animatedVisibilityScope,
            enter = fadeIn(),
            exit = fadeOut(),
            resizeMode = SharedTransitionScope.ResizeMode.ScaleToBounds(),
            boundsTransform = { _, _ ->
                tween(durationMillis = 300)
            }
        )
    }
}

enum class PokemonSharedElementType(val key: String) {
    IMAGE("image"),
    NAME("name")
}