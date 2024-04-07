package dev.agalperin.uikit.scaffold.topBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.agalperin.uikit.R
import dev.agalperin.uikit.theme.Black1
import dev.agalperin.uikit.theme.Grey00
import dev.agalperin.uikit.theme.Header1
import dev.agalperin.uikit.theme.Yellow1

@Composable
fun CoctailHeaderWithLogo(
    logoAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
//    navController: NavController,
    onReturnClick: () -> Unit,
    returnIcon: Painter = painterResource(id = R.drawable.baseline_chevron_left_24),
    showFavoritesIcon: Boolean = true,
//    viewModel: CocktailDetailViewModel = koinViewModel()
) {
//    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .height(44.dp)
            .fillMaxWidth()
            .background(Black1),
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth()
                .clickable { onReturnClick() }
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start

        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 10.dp),
                painter = returnIcon,
                contentDescription = stringResource(R.string.label_return),
                tint = Grey00
            )
            Text(
                modifier = Modifier.padding(bottom = 3.dp),
                text = "Description",
                style = Header1,
                color = Grey00,
            )
        }
        Image(
            modifier = Modifier
                .let {
                    when (logoAlignment) {
                        Alignment.CenterHorizontally -> it.align(Alignment.BottomCenter)
                        Alignment.Start -> it.align(Alignment.BottomStart)
                        else -> it.align(Alignment.BottomCenter)
                    }
                }
                .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                .width(127.dp)
                .height(19.dp),
            contentScale = ContentScale.Fit,
            painter = painterResource(R.drawable.empty_glass_icon),
            contentDescription = "logo"
        )
        if (showFavoritesIcon) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 8.dp)
                    .size(24.dp)
                    .clickable {
                       // viewModel.checkIfCocktailIsAdded(state.cocktail ?: return@clickable)
                    }
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = null,
                    tint = Grey00
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.align(Alignment.BottomCenter),
            thickness = 2.dp,
            color = Yellow1
        )
    }
}
