package com.euntaek.pokeshop.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPage<T>(
    @SerialName(value = "count") val count: Int,
    @SerialName(value = "next") val next: String?,
    @SerialName(value = "previous") val previous: String?,
    @SerialName(value = "results") val results: List<T>
) {
    companion object {
        const val PAGING_SIZE = 20
    }
}
