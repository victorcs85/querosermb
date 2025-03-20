package br.com.victorcs.querosermb.presentation.exchanges.ui.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ShowErrorMessage(errorMessage: String) {
    Text(errorMessage)
}

@Preview
@Composable
fun ShowErrorMessagePreview() {
    Text("No data available")
}