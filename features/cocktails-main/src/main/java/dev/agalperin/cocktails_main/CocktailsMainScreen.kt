package dev.agalperin.cocktails_main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import dev.agalperin.cocktails_main.components.CocktailSingleCard
import dev.agalperin.cocktails_main.models.UiCocktailMain
import dev.agalperin.uikit.components.AppLoader
import dev.agalperin.uikit.scaffold.CocktailsScaffold
import dev.agalperin.uikit.theme.CocktailsGuideTheme

class CocktailsMainScreen : Screen {
    @Composable
    override fun Content() {
        CocktailsScaffold { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                CocktailMainScreen(viewModel = viewModel())
            }
        }
    }
}


@Composable
internal fun CocktailMainScreen(viewModel: CocktailsMainViewModel) {
    val state by viewModel.state.collectAsState()
    val currentState = state

    if (state != State.None) {
        CocktailsMainContent(currentState)
    }
}


@Composable
private fun CocktailsMainContent(currentState: State) {
    Column {
        if (currentState is State.Error) {
            ErrorMessage(currentState)
        }
        if (currentState is State.Loading) {
            AppLoader()
        }
        if (currentState.cocktails != null) {
            Cocktails(cocktails = currentState.cocktails)
        }
    }
}

@Composable
private fun ErrorMessage(state: State.Error) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(CocktailsGuideTheme.colorScheme.error)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Error during update", color = CocktailsGuideTheme.colorScheme.onError)
    }
}

@Preview
@Composable
private fun Cocktails(
    @PreviewParameter(
        CocktailsPreviewProvider::class,
        limit = 1
    ) cocktails: List<UiCocktailMain>
) {
    LazyColumn(
    ) {
        items(cocktails) { cocktail ->
            key(cocktail.id) {
                CocktailSingleCard(cocktail = cocktail, onCocktailClicked = {})
            }
        }
    }
}

private class ArticlePreviewProvider : PreviewParameterProvider<UiCocktailMain> {

    override val values = sequenceOf(
        UiCocktailMain(
            id = "2",
            name = "Tyskie",
            category = "Beer",
            alcoholic = "Alcoholic",
            glass = "Mug"
        ),
        UiCocktailMain(
            id = "2",
            name = "Stolichnaya",
            category = "Vodka",
            alcoholic = "Alcoholic",
            glass = "shot"
        ),
        UiCocktailMain(
            id = "2",
            name = "Daiquiry",
            category = "Alcoholic",
            alcoholic = "Alcoholic",
            glass = "Margarita"
        ),
    )
}

private class CocktailsPreviewProvider : PreviewParameterProvider<List<UiCocktailMain>> {

    private val cocktailProvider = ArticlePreviewProvider()

    override val values = sequenceOf(
        cocktailProvider.values.toList()
    )
}