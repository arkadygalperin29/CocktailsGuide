package dev.agalperin.cocktailsguide

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.agalperin.network.CocktailsApi
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCocktailsApi(): CocktailsApi {
        return CocktailsApi(
            apiKey = BuildConfig.COCKTAILS_API_KEY,
            baseUrl = BuildConfig.COCKTAILS_API_BASE_URL
        )
    }
}