package dev.agalperin.data

import dev.agalperin.core.Logger
import dev.agalperin.data.models.Ingredient
import dev.agalperin.data.models.IngredientDetailed
import dev.agalperin.data.utils.toIngredient
import dev.agalperin.data.utils.toIngredientDBO
import dev.agalperin.data.utils.toIngredientDetailed
import dev.agalperin.data.utils.toIngredientDetailedDBO
import dev.agalperin.database.database.CocktailDatabase
import dev.agalperin.database.models.IngredientDBO
import dev.agalperin.network.CocktailsResponse
import dev.agalperin.network.CocktailsApi
import dev.agalperin.network.IngredientsResponse
import dev.agalperin.network.models.IngredientDTO
import dev.agalperin.network.models.IngredientDetailedDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class IngredientsRepository @Inject constructor(
    private val database: CocktailDatabase,
    private val api: CocktailsApi,
    private val logger: Logger
) {

    private companion object {
        const val LOG_TAG = "IngredientsRepository"
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getAll(
        mergeStrategy: MergeStrategy<RequestResult<List<Ingredient>>> = RequestResponseMergeStrategy()
    ): Flow<RequestResult<List<Ingredient>>> {

        database.cocktailDao.clean()

        val cachedAllIngredients = getAllFromDatabase()

        val remoteAllIngredients = getAllRemote()


        return cachedAllIngredients.combine(remoteAllIngredients, mergeStrategy::merge)
            .flatMapLatest { result ->
                if (result is RequestResult.Success) {
                    database.ingredientDao.observeAll()
                        .map { dbos ->
                            dbos.map { it.toIngredient() }
                        }.map { RequestResult.Success(it) }
                } else {
                    flowOf(result)
                }
            }

    }

    private suspend fun saveNetResponseToCacheList(data: List<IngredientDTO>) {
        val dbos = data.map { ingredientDTO -> ingredientDTO.toIngredientDBO() }
        database.ingredientDao.insertList(dbos)
    }

    private suspend fun saveNetResponseToCacheDetailed(data: IngredientDetailedDTO) {
        val dbo = data.toIngredientDetailedDBO()
        database.ingredientDao.insertSingle(dbo)
    }

    suspend fun getIngredientByName(name: String): IngredientDetailed {
        return api.getIngredientByIngredientsName(name).ingredients.first().toIngredientDetailed()
    }

    private fun getAllRemote(): Flow<RequestResult<List<Ingredient>>> {
        val apiRequest = flow {
            emit(api.getIngredientsList())
        }.onEach { result ->
            if (result.isSuccess) {
                saveNetResponseToCacheList(result.getOrThrow().cocktails)
            }
        }
            .onEach { result ->
                if (result.isFailure) {
                    logger.e(
                        LOG_TAG,
                        "Error getting from server. Cause: ${result.exceptionOrNull()}"
                    )
                }
            }
            .map { it.toRequestResult() }

        val start =
            flowOf<RequestResult<CocktailsResponse<IngredientDTO>>>(RequestResult.InProgress())

        return merge(start, apiRequest).map { result ->
            result.map { responseDTO ->
                responseDTO.cocktails.map { it.toIngredient() }
            }
        }
    }

    private fun getAllFromDatabase(): Flow<RequestResult<List<Ingredient>>> {
        val dbRequest = database.ingredientDao::getAll.asFlow()
            .map { RequestResult.Success(it) }
            .catch {
                RequestResult.Error<List<IngredientDBO>>(error = it)
                logger.e(tag = LOG_TAG, message = "Error getting from database. Cause: $it")
            }

        val start = flowOf<RequestResult<List<IngredientDBO>>>(RequestResult.InProgress())

        return merge(start, dbRequest).map { result ->
            result.map { articleDBOs ->
                articleDBOs.map {
                    it.toIngredient()
                }
            }
        }
    }
}