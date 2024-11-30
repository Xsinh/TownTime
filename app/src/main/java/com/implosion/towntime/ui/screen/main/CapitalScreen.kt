package com.implosion.towntime.ui.screen.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.implosion.towntime.R
import com.implosion.towntime.presentation.MainViewModel
import com.implosion.towntime.ui.screen.navigation.model.Screen
import com.implosion.towntime.ui.theme.TownTimeTheme
import com.implosion.towntime.ui.theme.Typography

@Composable
fun CapitalScreen(navController: NavController, viewModel: MainViewModel) {
    val selectedCapital = viewModel.selectedCapital.collectAsState()
    val time = viewModel.timeFlow.collectAsState("")
    TownTimeTheme {
        CapitalInfoComponent(
            capitalName = selectedCapital.value?.name.orEmpty(),
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
            .background(color = MaterialTheme.colorScheme.background)
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
            Text(stringResource(R.string.choose_capital_button))
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
        Text(
            capitalName,
            style = Typography.displayLarge,
            color = MaterialTheme.colorScheme.inverseSurface
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))

        var oldTime by remember { mutableStateOf(capitalTime) }

        SideEffect {
            oldTime = capitalTime
        }

        TimeAnimationText(capitalTime, oldTime)
    }
}

@Composable
private fun TimeAnimationText(capitalTime: String, oldTime: String) {
    Row {
        for (i in capitalTime.indices) {
            val oldChar = oldTime.getOrNull(i)
            val newChar = capitalTime[i]
            val char = if (oldChar == newChar) {
                oldTime[i]
            } else {
                capitalTime[i]
            }

            AnimatedContent(
                targetState = char,
                transitionSpec = {
                    slideInVertically { it } togetherWith (slideOutVertically { -it })
                }, label = "animationChar"
            ) { chars ->
                Text(
                    chars.toString(),
                    style = Typography.titleLarge,
                    color = MaterialTheme.colorScheme.inverseSurface
                )
            }
        }
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