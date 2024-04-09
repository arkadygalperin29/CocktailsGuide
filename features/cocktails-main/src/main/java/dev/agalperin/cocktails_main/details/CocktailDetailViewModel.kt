package dev.agalperin.cocktails_main.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.agalperin.cocktails_main.models.UiCocktailDetails
import dev.agalperin.cocktails_main.utils.toUiCocktailDetails
import dev.agalperin.core.AppDispatchers
import dev.agalperin.data.CocktailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailDetailViewModel @Inject constructor(
    private val cocktailsRepository: CocktailsRepository,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private val _cocktailDetailed = MutableStateFlow(UiCocktailDetails())
    val cocktailDetailed = _cocktailDetailed.asStateFlow()

    fun getCocktailById(id: String) {
        viewModelScope.launch(dispatchers.io) {
            _cocktailDetailed.value = cocktailsRepository.getCocktailById(id).toUiCocktailDetails()
        }
    }
}
