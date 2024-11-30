package com.implosion.towntime.ui.screen.capitallist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.implosion.towntime.ui.theme.Typography

@Composable
fun CapitalItem(name: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke()
            }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = name, style = Typography.titleLarge)
    }
}

@Preview
@Composable
fun CapitalItemPreview() {
    CapitalItem("test", onClick = {})
}