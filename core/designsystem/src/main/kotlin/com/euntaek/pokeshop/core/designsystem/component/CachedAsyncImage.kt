package com.euntaek.pokeshop.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.palette.graphics.Palette
import coil3.compose.SubcomposeAsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.allowHardware
import coil3.request.placeholder
import coil3.toBitmap

@Composable
fun CachedAsyncImage(
    imageURL: String,
    @DrawableRes placeholderDrawableResId: Int? = null,
    onPalette: ((Palette) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val imageRequest = ImageRequest.Builder(context)
        .data(imageURL)
        .diskCacheKey(imageURL)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCacheKey(imageURL)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .apply {
            if (placeholderDrawableResId != null) {
                this.placeholder(placeholderDrawableResId)
            }
        }
        .allowHardware(false) // Disable hardware bitmaps as Palette needs to read the image's pixels.
        .build()

    SubcomposeAsyncImage(
        modifier = modifier,
        contentScale = ContentScale.Fit,
        model = imageRequest,
        contentDescription = null,
        onSuccess = { result ->
            val bitmap = result.result.image.toBitmap()
            onPalette?.invoke(Palette.from(bitmap).generate())
        }
    )
}
