package com.euntaek.pokeshop.core.designsystem.util

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewScreenSizes


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    backgroundColor = 0xFFFCFCFC,
    name = "Light theme"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xFF201A1B,
    name = "Dark theme"
)
annotation class PreviewComponent


@PreviewFontScale
@PreviewScreenSizes
@PreviewComponent
annotation class PreviewPokeShopScreen