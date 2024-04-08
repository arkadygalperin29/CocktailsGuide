package dev.agalperin.cocktails_main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.agalperin.cocktails_main.models.UiCocktailMain
import dev.agalperin.data.RequestResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Provider

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
internal class CocktailsMainViewModel @Inject constructor(
    private val getAllCocktailsByQueryUsecase: Provider<GetAllCocktailsByQueryUsecase>
) : ViewModel() {

    private val _searchQueryState = MutableStateFlow("a")

    fun setSearchQuery(query: String) {
       _searchQueryState.value = query
    }

    val state: StateFlow<State> = _searchQueryState.flatMapLatest { query ->
        getAllCocktailsByQueryUsecase.get().invoke(query)
            .map { it.toState() }
    }.stateIn(viewModelScope, SharingStarted.Lazily, State.None)
}


private fun RequestResult<List<UiCocktailMain>>.toState(): State {
    return when (this) {
        is RequestResult.Error -> State.Error(data)
        is RequestResult.InProgress -> State.Loading(data)
        is RequestResult.Success -> State.Success(data)
    }
}


internal sealed class State(val cocktails: List<UiCocktailMain>? = null) {

    object None : State(cocktails = null)

    class Loading(cocktails: List<UiCocktailMain>? = null) : State(cocktails)

    class Error(cocktails: List<UiCocktailMain>? = null) : State(cocktails)

    class Success(cocktails: List<UiCocktailMain>) : State(cocktails)

}