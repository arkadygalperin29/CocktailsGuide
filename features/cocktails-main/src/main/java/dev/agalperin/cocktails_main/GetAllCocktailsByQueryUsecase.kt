package dev.agalperin.cocktails_main

import dev.agalperin.cocktails_main.models.UiCocktailMain
import dev.agalperin.cocktails_main.utils.toUiCocktailMain
import dev.agalperin.data.CocktailsRepository
import dev.agalperin.data.RequestResult
import dev.agalperin.data.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllCocktailsByQueryUsecase @Inject constructor(private val repository: CocktailsRepository) {

    internal suspend operator fun invoke(query: String): Flow<RequestResult<List<UiCocktailMain>>> {
        return repository.getAll(query = query)
            .map { requestResult ->
                requestResult.map { cocktails ->
                    cocktails.map { it.toUiCocktailMain() }
                }
            }
    }
}
