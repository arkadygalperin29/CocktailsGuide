package dev.agalperin.cocktails_main.details.cocktail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.AsyncImage
import dev.agalperin.cocktails_main.R
import dev.agalperin.cocktails_main.models.UiCocktailDetails
import dev.agalperin.uikit.scaffold.CocktailsScaffold
import dev.agalperin.uikit.theme.Aubergine
import dev.agalperin.uikit.theme.Black1
import dev.agalperin.uikit.theme.CoolWhite
import dev.agalperin.uikit.theme.Cream
import dev.agalperin.uikit.theme.Grey50
import dev.agalperin.uikit.theme.Header1
import dev.agalperin.uikit.theme.MiddleGreen
import dev.agalperin.uikit.theme.MintGreen
import dev.agalperin.uikit.theme.PaleGray
import dev.agalperin.uikit.theme.Peach
import dev.agalperin.uikit.theme.PeriWinkle1
import dev.agalperin.uikit.theme.Pink40
import dev.agalperin.uikit.theme.Red1
import dev.agalperin.uikit.theme.RoseGold1
import dev.agalperin.uikit.theme.SealBrown
import dev.agalperin.uikit.theme.SkyBlue
import dev.agalperin.uikit.theme.SoftBlueGray
import dev.agalperin.uikit.theme.Teal1
import dev.agalperin.uikit.theme.TerraCotta
import dev.agalperin.uikit.theme.Text14

data class CocktailsDetailScreen(val id: String): Screen {
    @Composable
    override fun Content() {

        val viewModel: CocktailDetailViewModel = getViewModel()
        val cocktailDetail by viewModel.cocktailDetailed.collectAsState()

        //To remove LaunchEffect and work around init when hilt will support @AssistedInject better
        LaunchedEffect(key1 = viewModel) {
            viewModel.getCocktailById(id)
        }

        CocktailsScaffold { paddingValues ->
            Column(
                modifier = Modifier
                    .background(color = Pink40)
                    .padding(paddingValues)
            ) {
                CocktailDetail(cocktail = cocktailDetail)
            }
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun CocktailDetail(
    cocktail: UiCocktailDetails
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Pink40)
            .offset(y = 16.dp)
            .verticalScroll(state = scrollState)
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
                model = cocktail.drinkImage,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        if (!cocktail.name.isNullOrEmpty()) {
            Text(
                text = cocktail.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                textAlign = TextAlign.Center,
                style = Header1,
                color = Grey50
            )
        }
        if (!cocktail.alcoholic.isNullOrEmpty()) {
            Text(
                text = stringResource(R.string.type, cocktail.alcoholic),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                style = Text14,
                color = Grey50
            )
        }
        if (!cocktail.category.isNullOrEmpty()) {
            Text(
                text = stringResource(R.string.category, cocktail.category),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                style = Text14,
                color = Grey50
            )
        }
        if (!cocktail.glass.isNullOrEmpty()) {
            Text(
                text = stringResource(R.string.glass, cocktail.glass),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                style = Text14,
                color = Grey50
            )
        }
        if (!cocktail.instructions.isNullOrEmpty()) {
            Text(
                text = stringResource(R.string.instructions, cocktail.instructions),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                style = Text14,
                color = Grey50
            )
        }
        if (!cocktail.tags.isNullOrEmpty()) {
            Text(
                text = stringResource(R.string.tags, cocktail.tags),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                style = Text14,
                color = Grey50
            )
        }
        if (!cocktail.videoLink.isNullOrEmpty()) {
            cocktail.videoLink.let {
                val link = stringResource(id = R.string.watch_video)
                val videoLinkAnnotated = buildAnnotatedString {
                    withAnnotation(
                        tag = "URL",
                        annotation = cocktail.videoLink
                    ) {
                        withStyle(
                            style = SpanStyle(
                                color = Red1,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append(link)
                        }
                    }
                }
                val uriHandler = LocalUriHandler.current
                ClickableText(
                    text = videoLinkAnnotated,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                    style = Text14,
                    onClick = {
                        videoLinkAnnotated.getStringAnnotations(tag = "URL", it, it)
                            .firstOrNull()?.let { stringAnnotation ->
                                uriHandler.openUri(stringAnnotation.item)
                            }
                    }
                )
            }
        }
        Text(
            text = stringResource(R.string.ingredients),
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
            style = Text14,
            color = Grey50
        )
        if (!cocktail.strIngredient1.isNullOrEmpty()) {
            cocktail.strIngredient1.let { name ->
                Row {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .border
                                (2.dp, color = Black1, RoundedCornerShape(8.dp))
                            .wrapContentSize()
                            .background(color = Cream, shape = RoundedCornerShape(8.dp))
                            .clickable { TODO("Navigation to ingredient") }
                    ) {
                        Text(
                            text = name,
                            modifier = Modifier.padding(8.dp),
                            style = Text14,
                            color = Black1
                        )
                    }
                    cocktail.strMeasure1?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                }
            }
        }
        if (!cocktail.strIngredient2.isNullOrEmpty()) {
            cocktail.strIngredient2.let { name ->
                Row {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .border
                                (2.dp, color = Black1, RoundedCornerShape(8.dp))
                            .wrapContentSize()
                            .background(color = RoseGold1, shape = RoundedCornerShape(8.dp))
                            .clickable { TODO("Navigation to ingredient") }
                    ) {
                        Text(
                            text = name,
                            modifier = Modifier
                                .padding(8.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                    cocktail.strMeasure2?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                }
            }
        }
        if (!cocktail.strIngredient3.isNullOrEmpty()) {
            cocktail.strIngredient3.let { name ->
                Row {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .border
                                (2.dp, color = Black1, RoundedCornerShape(8.dp))
                            .wrapContentSize()
                            .background(color = Peach, shape = RoundedCornerShape(8.dp))
                            .clickable { TODO("Navigation to ingredient") }
                    ) {
                        Text(
                            text = name,
                            modifier = Modifier
                                .padding(8.dp),
                            style = Text14,
                            color = Black1
                        )
                    }
                    cocktail.strMeasure3?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                }
            }
        }
        if (!cocktail.strIngredient4.isNullOrEmpty()) {
            cocktail.strIngredient4.let { name ->
                Row {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .border
                                (2.dp, color = Black1, RoundedCornerShape(8.dp))
                            .wrapContentSize()
                            .background(color = MintGreen, shape = RoundedCornerShape(8.dp))
                            .clickable { TODO("Navigation to ingredient") }
                    ) {
                        Text(
                            text = name,
                            modifier = Modifier
                                .padding(8.dp),
                            style = Text14,
                            color = Black1
                        )
                    }
                    cocktail.strMeasure4?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                }
            }
        }
        if (!cocktail.strIngredient5.isNullOrEmpty()) {
            cocktail.strIngredient5.let { name ->
                Row {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .border
                                (2.dp, color = Black1, RoundedCornerShape(8.dp))
                            .wrapContentSize()
                            .background(color = PaleGray, shape = RoundedCornerShape(8.dp))
                            .clickable { TODO("Navigation to ingredient") }
                    ) {
                        Text(
                            text = name,
                            modifier = Modifier
                                .padding(8.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                    cocktail.strMeasure5?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                }
            }
        }
        if (!cocktail.strIngredient6.isNullOrEmpty()) {
            cocktail.strIngredient6.let { name ->
                Row {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .border
                                (2.dp, color = Black1, RoundedCornerShape(8.dp))
                            .wrapContentSize()
                            .background(color = SealBrown, shape = RoundedCornerShape(8.dp))
                            .clickable { TODO("Navigation to ingredient") }
                    ) {
                        Text(
                            text = name,
                            modifier = Modifier
                                .padding(8.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                    cocktail.strMeasure6?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                }
            }
        }
        if (!cocktail.strIngredient7.isNullOrEmpty()) {
            cocktail.strIngredient7.let { name ->
                Row {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .border
                                (2.dp, color = Black1, RoundedCornerShape(8.dp))
                            .wrapContentSize()
                            .background(color = Teal1, shape = RoundedCornerShape(8.dp))
                            .clickable { TODO("Navigation to ingredient") }
                    ) {
                        Text(
                            text = name,
                            modifier = Modifier
                                .padding(8.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                    cocktail.strMeasure7?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                }
            }
        }
        if (!cocktail.strIngredient8.isNullOrEmpty()) {
            cocktail.strIngredient8.let { name ->
                Row {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .border
                                (2.dp, color = Black1, RoundedCornerShape(8.dp))
                            .wrapContentSize()
                            .background(color = CoolWhite, shape = RoundedCornerShape(8.dp))
                            .clickable { TODO("Navigation to ingredient") }
                    ) {
                        Text(
                            text = name,
                            modifier = Modifier
                                .padding(8.dp),
                            style = Text14,
                            color = Black1
                        )
                    }
                    cocktail.strMeasure8?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                }
            }
        }
        if (!cocktail.strIngredient9.isNullOrEmpty()) {
            cocktail.strIngredient9.let { name ->
                Row {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .border
                                (2.dp, color = Black1, RoundedCornerShape(8.dp))
                            .wrapContentSize()
                            .background(color = Aubergine, shape = RoundedCornerShape(8.dp))
                            .clickable { TODO("Navigation to ingredient") }
                    ) {
                        Text(
                            text = name,
                            modifier = Modifier
                                .padding(8.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                    cocktail.strMeasure9?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                }
            }
        }
        if (!cocktail.strIngredient10.isNullOrEmpty()) {
            cocktail.strIngredient10.let { name ->
                Row {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .border
                                (2.dp, color = Black1, RoundedCornerShape(8.dp))
                            .wrapContentSize()
                            .background(color = MiddleGreen, shape = RoundedCornerShape(8.dp))
                            .clickable { TODO("Navigation to ingredient") }
                    ) {
                        Text(
                            text = name,
                            modifier = Modifier
                                .padding(8.dp),
                            style = Text14,
                            color = Black1
                        )
                    }
                    cocktail.strMeasure10?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                }
            }
        }
        if (!cocktail.strIngredient11.isNullOrEmpty()) {
            cocktail.strIngredient11.let { name ->
                Row {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .border
                                (2.dp, color = Black1, RoundedCornerShape(8.dp))
                            .wrapContentSize()
                            .background(color = TerraCotta, shape = RoundedCornerShape(8.dp))
                            .clickable { TODO("Navigation to ingredient") }
                    ) {
                        Text(
                            text = name,
                            modifier = Modifier
                                .padding(8.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                    cocktail.strMeasure11?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                }
            }
        }
        if (!cocktail.strIngredient12.isNullOrEmpty()) {
            cocktail.strIngredient12.let { name ->
                Row {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .border
                                (2.dp, color = Black1, RoundedCornerShape(8.dp))
                            .wrapContentSize()
                            .background(color = SoftBlueGray, shape = RoundedCornerShape(8.dp))
                            .clickable { TODO("Navigation to ingredient") }
                    ) {
                        Text(
                            text = name,
                            modifier = Modifier
                                .padding(8.dp),
                            style = Text14,
                            color = Black1
                        )
                    }
                    cocktail.strMeasure12?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                }
            }
        }
        if (!cocktail.strIngredient13.isNullOrEmpty()) {
            cocktail.strIngredient13.let { name ->
                Row {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .border
                                (2.dp, color = Black1, RoundedCornerShape(8.dp))
                            .wrapContentSize()
                            .background(color = PeriWinkle1, shape = RoundedCornerShape(8.dp))
                            .clickable { TODO("Navigation to ingredient") }
                    ) {
                        Text(
                            text = name,
                            modifier = Modifier
                                .padding(8.dp),
                            style = Text14,
                            color = Black1
                        )
                    }
                    cocktail.strMeasure13?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                }
            }
        }
        if (!cocktail.strIngredient14.isNullOrEmpty()) {
            cocktail.strIngredient14.let { name ->
                Row {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .border
                                (2.dp, color = Black1, RoundedCornerShape(8.dp))
                            .wrapContentSize()
                            .background(color = SkyBlue, shape = RoundedCornerShape(8.dp))
                            .clickable { TODO("Navigation to ingredient") }
                    ) {
                        Text(
                            text = name,
                            modifier = Modifier
                                .padding(8.dp),
                            style = Text14,
                            color = Black1
                        )
                    }
                    cocktail.strMeasure14?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                }
            }
        }
        if (!cocktail.strIngredient15.isNullOrEmpty()) {
            cocktail.strIngredient15.let { name ->
                Row {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .border
                                (2.dp, color = Black1, RoundedCornerShape(8.dp))
                            .wrapContentSize()
                            .background(color = SkyBlue, shape = RoundedCornerShape(8.dp))
                            .clickable { TODO("Navigation to ingredient by name") }
                    ) {
                        Text(
                            text = name,
                            modifier = Modifier
                                .padding(8.dp),
                            style = Text14,
                            color = Black1
                        )
                    }
                    cocktail.strMeasure15?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp),
                            style = Text14,
                            color = Grey50
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}


@Preview
@Composable
fun CocktailDetailPreview() {
    CocktailDetail(
        cocktail = UiCocktailDetails(
            id = "1",
            name = "Margarita",
            category = "Alcoholic",
            alcoholic = "20%",
            glass = "simple",
            instructions = "Sprinkle a few teaspoons of salt over the surface of a small plate or saucer. Rub one wedge of lime along the rim of a tumbler and then dip it into the salt so that the entire rim is covered.",
            drinkImage = "1",
            strIngredient1 = "vodka",
            strIngredient2 = "Nice one",
            strIngredient3 = null,
            strIngredient4 = "124312412412",
            strIngredient5 = "123",
            strIngredient6 = "VODKA VODKA",
            strIngredient7 = null,
            strIngredient8 = null,
            strIngredient9 = null,
            strIngredient10 = null,
            strIngredient11 = null,
            strIngredient12 = null,
            strIngredient13 = null,
            strIngredient14 = null
        )
    )
}




