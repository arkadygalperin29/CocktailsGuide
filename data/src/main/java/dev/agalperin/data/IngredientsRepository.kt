package dev.agalperin.data

import dev.agalperin.core.Logger
import dev.agalperin.data.utils.toCocktailDBO
import dev.agalperin.database.database.CocktailDatabase
import dev.agalperin.network.CocktailsApi
import dev.agalperin.network.models.CocktailDTO
import javax.inject.Inject

class IngredientsRepository @Inject constructor(
    private val database: CocktailDatabase,
    private val api: CocktailsApi,
    private val logger: Logger
) {


    private suspend fun saveNetResponseToCache(data: List<CocktailDTO>) {
        val dbos = data.map { cocktailDTO -> cocktailDTO.toCocktailDBO() }
        database.cocktailDao.insert(dbos)
    }
}