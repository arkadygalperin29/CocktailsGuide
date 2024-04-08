package dev.agalperin.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider
import cafe.adriel.voyager.core.registry.screenModule

sealed class SharedScreen : ScreenProvider {
    object CocktailMainScreen : SharedScreen()
    data class CocktailMainDetailScreen(val id: String) : SharedScreen()
}