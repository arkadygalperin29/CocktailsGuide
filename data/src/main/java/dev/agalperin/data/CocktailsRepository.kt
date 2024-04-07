package dev.agalperin.data

import dev.agalperin.core.Logger
import dev.agalperin.data.models.Cocktail
import dev.agalperin.data.utils.toCocktail
import dev.agalperin.data.utils.toCocktailDBO
import dev.agalperin.database.database.CocktailDatabase
import dev.agalperin.database.models.CocktailDBO
import dev.agalperin.network.ApiResponse
import dev.agalperin.network.CocktailsApi
import dev.agalperin.network.models.CocktailDTO
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

class CocktailsRepository @Inject constructor(
    private val database: CocktailDatabase,
    private val api: CocktailsApi,
    private val logger: Logger
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getAll(
        query: String,
        mergeStrategy: MergeStrategy<RequestResult<List<Cocktail>>> = RequestResponseMergeStrategy()
    ): Flow<RequestResult<List<Cocktail>>> {

        val cachedAllArticles = getAllFromDatabase()

        val remoteArticles = getAllRemote(query)


        return cachedAllArticles.combine(remoteArticles, mergeStrategy::merge)
            .flatMapLatest { result ->
                if (result is RequestResult.Success) {
                    database.cocktailDao.observeAll()
                        .map { dbos ->
                            dbos.map { it.toCocktail() }
                        }.map { RequestResult.Success(it) }
                } else {
                    flowOf(result)
                }
            }

    }

    private fun getAllRemote(query: String): Flow<RequestResult<List<Cocktail>>> {
        val apiRequest = flow {
            emit(api.getCocktailsByFirstLetter(letterSearch = query))
        }.onEach { result ->
            if (result.isSuccess) {
                saveNetResponseToCache(result.getOrThrow().cocktails)
            }
        }
            .onEach { result ->
                if (result.isFailure) {
                    logger.e(LOG_TAG, "Error getting from server. Cause: ${result.exceptionOrNull()}")
                }
            }
            .map { it.toRequestResult() }

        val start = flowOf<RequestResult<ApiResponse<CocktailDTO>>>(RequestResult.InProgress())

        return merge(start, apiRequest).map { result ->
            result.map { responseDTO ->
                responseDTO.cocktails.map { it.toCocktail() }
            }
        }
    }

    private suspend fun saveNetResponseToCache(data: List<CocktailDTO>) {
        val dbos = data.map { cocktailDTO -> cocktailDTO.toCocktailDBO() }
        database.cocktailDao.insert(dbos)
    }

    private fun getAllFromDatabase(): Flow<RequestResult<List<Cocktail>>> {
        val dbRequest = database.cocktailDao::getAll.asFlow()
            .map { RequestResult.Success(it) }
            .catch {
                RequestResult.Error<List<CocktailDBO>>(error = it)
                logger.e(tag = LOG_TAG, message = "Error getting from database. Cause: $it")
            }

        val start = flowOf<RequestResult<List<CocktailDBO>>>(RequestResult.InProgress())

        return merge(start, dbRequest).map { result ->
            result.map { articleDBOs ->
                articleDBOs.map {
                    it.toCocktail()
                }
            }
        }
    }
    private companion object {
        const val LOG_TAG = "CocktailsRepository"
    }

    suspend fun search(query: String): Flow<Cocktail> {
        api.getCocktailsByFirstLetter(query)
        TODO("NOT YET IMPLEMENTED")
    }

    fun fetchLatest(query: String): Flow<RequestResult<List<Cocktail>>> {
        return getAllRemote(query)
    }
}
