package com.euntaek.pokeshop.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URI

/**
 * Network representation of [NetworkPokemon]
 */
@Serializable
data class NetworkPokemon(
    @SerialName(value = "name") val name: String,
    @SerialName(value = "url") val url: String
) {
    companion object {
        private const val BASE_IMAGE_URL =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/"
    }

    val id: Int
        get() = URI(url).path.split("/").last { it.isNotEmpty() }.toInt()

    val imageUrl: String
        get() = BASE_IMAGE_URL + "official-artwork/$id.png"

    val gifUrl: String
        get() = BASE_IMAGE_URL + "showdown/$id.gif"
}
