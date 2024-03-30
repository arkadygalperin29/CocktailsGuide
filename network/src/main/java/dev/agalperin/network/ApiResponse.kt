package dev.agalperin.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T : Any>(
    @SerialName("drinks")
    val data: T?
)