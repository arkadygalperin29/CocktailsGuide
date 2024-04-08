package dev.agalperin.cocktails_main.utils

import dev.agalperin.cocktails_main.models.UiCocktailMain
import dev.agalperin.data.models.Cocktail

fun Cocktail.toUiCocktailMain(): UiCocktailMain {
    return UiCocktailMain(
        id = id,
        name = name,
        category = category,
        alcoholic = alcoholic,
        glass = glass,
        drinkImage = drinkImage
    )
}