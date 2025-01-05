package com.euntaek.pokeshop.core.designsystem.component

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun PokeShopText(
    text: String,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    style: TextStyle = TextStyle.Default,
    modifier: Modifier = Modifier
) {
    BasicText(
        modifier = modifier,
        text = text,
        maxLines = maxLines,
        overflow = overflow,
        style = style
    )
}

@Preview
@Composable
private fun PokeShopTextPreview() {
    PokeShopText(text = "pokemon")
}