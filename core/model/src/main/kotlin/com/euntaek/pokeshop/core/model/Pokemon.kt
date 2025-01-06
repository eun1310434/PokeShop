package com.euntaek.pokeshop.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int,
    val name: String,
    val url: String,
    val imageUrl: String,
    val gifUrl: String,
)

