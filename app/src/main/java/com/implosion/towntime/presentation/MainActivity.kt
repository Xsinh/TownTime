package com.implosion.towntime.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.implosion.towntime.ui.screen.navigation.AppNavigation
import com.implosion.towntime.ui.theme.TownTimeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val myViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TownTimeTheme {
                AppNavigation()
            }
        }
    }
}
