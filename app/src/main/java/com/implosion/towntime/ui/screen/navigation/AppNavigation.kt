package com.implosion.towntime.ui.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.implosion.towntime.presentation.MainViewModel
import com.implosion.towntime.ui.screen.capitallist.CapitalListScreen
import com.implosion.towntime.ui.screen.main.CapitalScreen
import com.implosion.towntime.ui.screen.navigation.model.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = koinViewModel()
    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) { CapitalScreen(navController, viewModel) }
        composable(Screen.CapitalListScreen.route) { CapitalListScreen(navController, viewModel) }
    }
}