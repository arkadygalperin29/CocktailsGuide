package dev.agalperin.uikit.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Grey1000 = Color(0xFF1F1F1F)
val Grey900 = Color(0xFF2C2C2C)
val Grey800 = Color(0xFF4C4C4C)
val Grey500 = Color(0xFF6D6D6D)
val Grey400 = Color(0xFF929292)
val Grey200 = Color(0xFFDADADA)
val Grey100 = Color(0xFFF5F5F5)
val Grey50 = Color(0xFFFCFCFC)
val Grey00 = Color(0xFFFFFFFF)
val Yellow1 = Color(0xFFFFD500)
val Yellow2 = Color(0xFFE3BE00)
val Green1 = Color(0xFF57BA47)
val Black1 = Color(0xFF000000)
val Red1 = Color(0xFFCE592C)
val RedClean = Color(0xFFFF0000)
val Blue1 = Color(0xFF3870B3)
val Blue2 = Color(0xFF6899D3)
val Purple1 = Color(0xFFCC33CC)
val Teal1 = Color(0xFF008080)
val Brown1 = Color(0xFF7A5C58)
val RoseGold1 = Color(0xFFB76E79)
val PeriWinkle1 = Color(0xFFCCCCFF) // use with Black Text
val MintGreen = Color(0xFF99EDC3) //use with Black Text
val PaleGray = Color(0xFF7286A0) //use with White Text
val SealBrown = Color(0xFF59260B) //use with White Text
val Cream = Color(0xFFFFFDD0) //use with Black Text
val CoolWhite = Color(0xFFF4FDFF) //use with Black Text
val MiddleGreen = Color(0xFFA4C2A5) //Use with Black Text
val Aubergine = Color(0xFF472C4C) //With White text
val TerraCotta = Color(0xFFE2725B) // With White Text
val SoftBlueGray = Color(0xFFB7C9E2) // With Black Text
val Peach = Color(0xFFFFCDA2) // With Black Text
val SkyBlue = Color(0xFF87CEEB) //with Black Text


data class AppButtonColorsFilled(
    val containerColor: Color = Yellow1,
    val contentColor: Color = Grey1000,
    val disabledContainerColor: Color = Grey400,
    val disabledContentColor: Color = Grey800
)

data class AppButtonColorsOutlined(
    val containerColor: Color = Color.Transparent,
    val contentColor: Color = Yellow1,
    val disabledContainerColor: Color = Color.Transparent,
    val disabledContentColor: Color = Grey400
)