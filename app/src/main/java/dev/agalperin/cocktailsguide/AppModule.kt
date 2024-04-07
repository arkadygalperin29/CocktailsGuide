package dev.agalperin.cocktailsguide

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.agalperin.database.database.CocktailDatabase
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

    @Provides
    @Singleton
    fun provideCocktailDatabase(@ApplicationContext context: Context): CocktailDatabase  {
        return CocktailDatabase(context)
    }
}