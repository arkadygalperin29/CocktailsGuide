package dev.agalperin.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IngredientsResponse<E>(
    @SerialName("ingredients")
    val ingredients: List<E>
)