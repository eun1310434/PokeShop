package com.euntaek.pokeshop.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Network representation of [NetworkPokemonSpec]
 */
@Serializable
data class NetworkPokemonSpec(
    @SerialName(value = "id") val id: Int,
    @SerialName(value = "name") val name: String,
    @SerialName(value = "height") val height: Int,
    @SerialName(value = "weight") val weight: Int
)
