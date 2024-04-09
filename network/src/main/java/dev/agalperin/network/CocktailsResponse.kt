package dev.agalperin.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CocktailsResponse<E>(
    @SerialName("drinks")
    val cocktails: List<E>
)