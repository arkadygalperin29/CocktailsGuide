package dev.agalperin.cocktails_main.details.ingredient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.agalperin.cocktails_main.models.UiIngredientDetails
import dev.agalperin.cocktails_main.utils.toUiIngredientDetails
import dev.agalperin.core.AppDispatchers
import dev.agalperin.data.IngredientsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class IngredientViewModel @Inject constructor(
    private val ingredientsRepository: IngredientsRepository,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private val _ingredientDetailed = MutableStateFlow(UiIngredientDetails())
    val ingredientDetailed = _ingredientDetailed.asStateFlow()

    fun fetchIngredientDetailsByName(name: String) {
        viewModelScope.launch(dispatchers.io) {
            _ingredientDetailed.value = ingredientsRepository.getIngredientByName(name).toUiIngredientDetails()
        }
    }
}