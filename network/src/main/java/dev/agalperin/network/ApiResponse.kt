package dev.agalperin.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<E>(
    @SerialName("drinks")
    val cocktails: List<E>
)