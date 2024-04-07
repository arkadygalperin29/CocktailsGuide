package dev.agalperin.uikit.scaffold.utils

import androidx.annotation.DrawableRes
import androidx.compose.ui.Alignment
import dev.agalperin.uikit.R

sealed class AppHeaderType {
    data class WithBackButton(
        val logoAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
        val onReturnClick: () -> Unit,
        @DrawableRes val returnIconResId: Int = R.drawable.baseline_chevron_left_24
    ) : AppHeaderType()

    data class WithoutBackButton(
        val logoAlignment: Alignment.Horizontal = Alignment.CenterHorizontally
    ) : AppHeaderType()

    object None : AppHeaderType()
}