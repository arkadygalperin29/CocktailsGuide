package dev.agalperin.data.models

data class IngredientDetailed(
    val id: String = "1",
    val name: String? = null,
    val description: String? = null,
    val drinkType: String? = null,
    val isAlcoholic: String? = null,
    val alcoholicVolume: String? = null
)
