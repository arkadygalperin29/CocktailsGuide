package dev.agalperin.network.models

import androidx.annotation.DrawableRes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IngredientDTO(
    @SerialName("strIngredient1")
    val ingredientName: String,
    @DrawableRes
    val image: Int? = null,
    val description: String? = null
)