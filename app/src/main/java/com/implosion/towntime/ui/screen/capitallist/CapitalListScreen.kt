package com.implosion.towntime.ui.screen.capitallist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.implosion.towntime.presentation.MainViewModel
import com.implosion.towntime.ui.screen.navigation.model.Screen
import com.implosion.towntime.ui.theme.TownTimeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CapitalListScreen(navController: NavController, viewModel: MainViewModel) {
    val bottomSheetState = rememberModalBottomSheetState()
    val capitals = remember { viewModel.capitalMutableState.value }

    TownTimeTheme {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            content = {
                LazyColumn {
                    items(capitals) { item ->
                        CapitalItem(item.name) {
                            viewModel.selectCapital(item)
                            navController.navigate(Screen.Main.route)
                        }
                    }
                }
            },
            onDismissRequest = { navController.navigate(Screen.Main.route) }
        )
    }
}
