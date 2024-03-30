package dev.agalperin.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IngredientsResponse<T: Any>(
    @SerialName("ingredients")
    val data: T?
)