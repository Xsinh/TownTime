package com.implosion.towntime.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.implosion.towntime.presentation.MainViewModel
import com.implosion.towntime.ui.screen.navigation.model.Screen
import com.implosion.towntime.ui.theme.TownTimeTheme
import com.implosion.towntime.ui.theme.Typography
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun CapitalScreen(navController: NavController, viewModel: MainViewModel) {
    val selectedCapital = viewModel.selectedCapital.collectAsState()
    val time = viewModel.timeFlow.collectAsState("hmm")
    TownTimeTheme {
        CapitalInfoComponent(
            capitalName = selectedCapital.value,
            capitalTime = time.value,
            onChangeCapitalClick = {
                navController.navigate(Screen.CapitalListScreen.route)
            })
    }
}

@Composable
private fun CapitalInfoComponent(
    capitalName: String,
    capitalTime: String,
    onChangeCapitalClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        CapitalTimeInfo(
            modifier = Modifier
                .padding(top = 256.dp)
                .align(Alignment.TopCenter),
            capitalName = capitalName,
            capitalTime = capitalTime,
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .align(Alignment.BottomCenter),
            onClick = { onChangeCapitalClick.invoke() }
        ) {
            Text("Choose a Сapital")
        }
    }
}

@Composable
private fun CapitalTimeInfo(
    capitalName: String,
    capitalTime: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(capitalName, style = Typography.displayLarge)
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Text(capitalTime, style = Typography.titleLarge)
    }
}

@Preview(showBackground = true)
@Composable
private fun CapitalInfoComponentPreview() {
    CapitalInfoComponent(
        capitalName = "Capital",
        capitalTime = "Time",
        onChangeCapitalClick = {}
    )
}