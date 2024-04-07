package dev.agalperin.uikit.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.agalperin.uikit.R

private val rubikFontFamily = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.rubik,
            weight = FontWeight.W400,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.rubik_italic,
            weight = FontWeight.W400,
            style = FontStyle.Italic
        ),
        Font(
            resId = R.font.rubik_medium,
            weight = FontWeight.W500,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.rubik_medium_italic,
            weight = FontWeight.W500,
            style = FontStyle.Italic
        ),
    )
)


val Header1 = TextStyle(
    fontFamily = rubikFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 18.sp,
    lineHeight = 21.sp,
)

val Header2 = TextStyle(
    fontFamily = rubikFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 26.sp,
)

val Text24 = TextStyle(
    fontFamily = rubikFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 24.sp,
    lineHeight = 28.44.sp
)

val Text14 = TextStyle(
    fontFamily = rubikFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 18.sp,
)

val Text14button = TextStyle(
    fontFamily = rubikFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    lineHeight = 22.sp,
)

val Text12 = TextStyle(
    fontFamily = rubikFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 14.sp,
)

val Text12E = TextStyle(
    fontFamily = rubikFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
)

val Text12medium = TextStyle(
    fontFamily = rubikFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    lineHeight = 14.sp,
)

val Text10 = TextStyle(
    fontFamily = rubikFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 10.sp,
    lineHeight = 12.sp,
)

val Typography = Typography(
    titleLarge = Header1,
    titleMedium = Header2,
    bodyLarge = Text14, // must be set to Text 14 for default unfocused text field label size
    bodyMedium = Text14,
    bodySmall = Text12, // must be set to Text 12 for default focused text field label size
    labelMedium = Text14button,
    labelSmall = Text12medium
)