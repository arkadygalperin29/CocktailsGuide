package dev.agalperin.cocktailsguide

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import dagger.hilt.android.HiltAndroidApp
import dev.agalperin.cocktails_main.CocktailsMainScreen
import dev.agalperin.cocktails_main.details.CocktailsDetailScreen
import dev.agalperin.navigation.SharedScreen

@HiltAndroidApp
class CocktailApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        ScreenRegistry {
            register<SharedScreen.CocktailMainScreen> {
                CocktailsMainScreen()
            }
            register<SharedScreen.CocktailMainDetailScreen> { provider ->
                CocktailsDetailScreen(provider.id)
            }
        }
    }
}