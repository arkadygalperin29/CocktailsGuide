package dev.agalperin.uikit.scaffold.utils

sealed class AppBottomBarType {
    object Normal : AppBottomBarType()
    object None : AppBottomBarType()
}