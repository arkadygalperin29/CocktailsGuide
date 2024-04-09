package dev.agalperin.uikit.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.agalperin.uikit.scaffold.utils.AppBottomBarType
import dev.agalperin.uikit.scaffold.utils.AppHeaderType
import dev.agalperin.uikit.scaffold.bottomBar.CoctailBottomNavigation
import dev.agalperin.uikit.scaffold.topBar.CoctailHeaderWithLogo
import dev.agalperin.uikit.theme.Grey1000

@Composable
fun CocktailsScaffold(
    modifier: Modifier = Modifier,
    topBarType: AppHeaderType? = AppHeaderType.WithBackButton(onReturnClick = { }),
    bottomBarType: AppBottomBarType = AppBottomBarType.Normal,
    contentColor: Color = LocalContentColor.current,
    containerColor: Color = Color.Transparent,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier.then(
            Modifier
                .systemBarsPadding()
                .imePadding()
        ),
        topBar = {
            CompositionLocalProvider(LocalContentColor provides Grey1000) {
                when (topBarType) {
                    is AppHeaderType.WithBackButton -> {
                        CoctailHeaderWithLogo(
                            logoAlignment = topBarType.logoAlignment,
                            onReturnClick = topBarType.onReturnClick,
                            returnIcon = painterResource(id = topBarType.returnIconResId)
                        )
                    }

                    is AppHeaderType.WithoutBackButton -> {
                        CoctailHeaderWithLogo(
                            logoAlignment = topBarType.logoAlignment,
                            showFavoritesIcon = false,
                            onReturnClick = {}
                        )
                    }

                    is AppHeaderType.None -> {
                        Box {}
                    }

                    else -> {}
                }
            }
        },
        bottomBar = {
            when (bottomBarType) {
                is AppBottomBarType.Normal -> {
                    CoctailBottomNavigation(
                        modifier = Modifier.wrapContentHeight()
                    )
                }
                is AppBottomBarType.None -> {
                    Box {}
                }
            }
        },
        contentColor = contentColor,
        containerColor = containerColor,
    ) {
        content(it)
    }
}