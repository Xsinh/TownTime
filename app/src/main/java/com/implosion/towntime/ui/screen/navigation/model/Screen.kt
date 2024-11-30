package com.implosion.towntime.ui.screen.navigation.model

sealed class Screen(val route: String) {
    data object Main : Screen("home")
    data object Details : Screen("details/{itemId}") {
        fun createRoute(itemId: String) = "details/$itemId"
    }

    data object CapitalListScreen : Screen("capital_screen")
}