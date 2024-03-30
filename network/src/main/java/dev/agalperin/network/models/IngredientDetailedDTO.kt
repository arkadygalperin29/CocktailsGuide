package dev.agalperin.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IngredientDetailedDTO(
    @SerialName("idIngredient")
    val id: String? = null,
    @SerialName("strIngredient")
    val name: String? = null,
    @SerialName("strDescription")
    val description: String? = null,
    @SerialName("strType")
    val drinkType: String? = null,
    @SerialName("strAlcohol")
    val isAlcoholic: String? = null,
    @SerialName("strABV")
    val alcoholicVolume: String? = null
)