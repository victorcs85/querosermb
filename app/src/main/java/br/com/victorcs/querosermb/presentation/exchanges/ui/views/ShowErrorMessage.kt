package br.com.victorcs.querosermb.presentation.exchanges.ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShowErrorMessage(errorMessage: String) {
    Text(text = errorMessage, modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp))
}

@Preview
@Composable
fun ShowErrorMessagePreview() {
    Text("No data available")
}