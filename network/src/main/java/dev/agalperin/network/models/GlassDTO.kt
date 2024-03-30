package dev.agalperin.network.models

import androidx.annotation.DrawableRes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GlassDTO(
    @SerialName("strGlass")
    val name: String,
    @DrawableRes
    val imageRes: Int? = null,
    val description: String? = null
)