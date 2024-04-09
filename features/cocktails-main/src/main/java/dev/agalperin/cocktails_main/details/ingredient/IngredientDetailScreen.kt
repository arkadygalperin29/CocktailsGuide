package dev.agalperin.cocktails_main.details.ingredient

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.AsyncImage
import dev.agalperin.cocktails_main.R
import dev.agalperin.cocktails_main.models.UiIngredientDetails
import dev.agalperin.uikit.scaffold.CocktailsScaffold
import dev.agalperin.uikit.theme.Black1
import dev.agalperin.uikit.theme.Grey50
import dev.agalperin.uikit.theme.Header1
import dev.agalperin.uikit.theme.Pink40
import dev.agalperin.uikit.theme.Text14

data class IngredientDetailScreen(val name: String): Screen {
    @Composable
    override fun Content() {

        val viewModel: IngredientViewModel = getViewModel()
        val scrollState = rememberScrollState()
        val ingredientDetails by viewModel.ingredientDetailed.collectAsState()

        LaunchedEffect(true) {
            viewModel.fetchIngredientDetailsByName(name.dropLast(1))
        }

        CocktailsScaffold { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Pink40)
                    .padding(paddingValues)
                    .verticalScroll(state = scrollState),
            ) {
                IngredientDetail(ingredientDetailed = ingredientDetails)
            }
        }
    }
}

@Composable
fun IngredientDetail(
    ingredientDetailed: UiIngredientDetails,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink40)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                .background(color = Pink40)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(color = Color.Black)
                    .border(2.dp, Black1, shape = RoundedCornerShape(16.dp)),
                model = stringResource(
                    R.string.coil_image_url_ingredients,
                    ingredientDetailed.name.toString()
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        if (!ingredientDetailed.name.isNullOrEmpty()) {
            ingredientDetailed.name?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    textAlign = TextAlign.Center,
                    style = Header1,
                    color = Grey50
                )
            }
        }
        if (!ingredientDetailed.drinkType.isNullOrEmpty()) {
            Text(
                text = stringResource(
                    R.string.drink_type_ingredient_detail,
                    ingredientDetailed.drinkType
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp),
                textAlign = TextAlign.Start,
                style = Text14,
                color = Grey50
            )
        }
        if (!ingredientDetailed.isAlcoholic.isNullOrEmpty()) {
            Text(
                text = stringResource(R.string.is_alcoholic, ingredientDetailed.isAlcoholic),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp),
                textAlign = TextAlign.Start,
                style = Text14,
                color = Grey50
            )
        }
        if (!ingredientDetailed.alcoholicVolume.isNullOrEmpty()) {
            Text(
                text = "Alcohol volume: ${ingredientDetailed.alcoholicVolume}%",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp),
                textAlign = TextAlign.Start,
                style = Text14,
                color = Grey50
            )
        }
        if (!ingredientDetailed.description.isNullOrEmpty()) {
            Text(
                text = "Description: ${ingredientDetailed.description}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp),
                textAlign = TextAlign.Start,
                style = Text14,
                color = Grey50
            )
        }
    }
}

@Preview
@Composable
fun IngredientDetailPreview() {
    IngredientDetail(
        ingredientDetailed = UiIngredientDetails(
            "123",
            "Vodka",
            "Pour this vodka in a glass and drink it",
            "Square",
            "Alcoholic",
            "45"
        )
    )
}
