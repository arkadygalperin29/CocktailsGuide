package dev.agalperin.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.agalperin.uikit.R
import dev.agalperin.uikit.theme.Grey400
import dev.agalperin.uikit.theme.Text12
import dev.agalperin.uikit.theme.Yellow1

@Composable
fun AppNavigationBarItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    label: String,
    selected: Boolean,
    selectedMarkerEnabled: Boolean = true,
    selectedColor: Color = Color.White
) {
    Column(
        modifier = modifier
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (selectedMarkerEnabled){
            Spacer(
                modifier = Modifier
                    .let {
                        if (selected) it.background(Color.White) else it.background(
                            Color.Transparent
                        )
                    }
                    .height(2.dp)
                    .width(55.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            icon()
            Text(
                text = label,
                color = if (selected) selectedColor else Grey400,
                style = Text12
            )
        }
    }
}


@Preview
@Composable
private fun PreviewComponent() {
    AppNavigationBarItem(
        onClick = { },
        icon = {
            Icon(
                modifier = Modifier
                    .padding(bottom = 6.dp)
                    .size(24.dp),
                painter = painterResource(id = R.drawable.alcoholic_percentage),
                contentDescription = "icon desc",
                tint = Grey400,
            )
        },
        label = "Label",
        selected = false,
    )
}

@Preview
@Composable
private fun PreviewComponentSelected() {
    AppNavigationBarItem(
        onClick = { },
        icon = {
            Icon(
                modifier = Modifier
                    .padding(bottom = 6.dp)
                    .size(24.dp),
                painter = painterResource(id = R.drawable.empty_glass_icon),
                contentDescription = "icon desc",
                tint = Yellow1,
            )
        },
        label = "Label",
        selected = true,
    )
}