package dev.agalperin.uikit.scaffold.bottomBar

import dev.agalperin.uikit.R

sealed class BottomNavItem(
    var title: String,
    var icon: Int,
    var screenRoute: String,
    var action: () -> Unit = {}
) {


    data object Start :
        BottomNavItem(
            "Start",
            R.drawable.baseline_home_24,
            "Menu")
//            { navigateToMenu() })

    data object Categories :
        BottomNavItem(
            "Categories",
            R.drawable.categories,
            "Categories",)
//            { navigateToCategory(CategoriesSelection.ALCOHOLIC) })

    data object Cocktails :
        BottomNavItem(
            "Coctails",
            R.drawable.cocktail,
            "FavoriteCocktails")
//            { navigateToFavorites()}

    data object SortByDegree :
        BottomNavItem(
            "Ingredients",
            R.drawable.ingredients,
            "IngredientsRoute")
//            { navigateToIngredients() })

    data object Glasses :
        BottomNavItem(
            "Glasses",
            R.drawable.baseline_cup,
            "GlassesRoute")
//            { navigateToGlasses() })
}