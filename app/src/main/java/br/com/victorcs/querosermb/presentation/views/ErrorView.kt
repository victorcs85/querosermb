package br.com.victorcs.querosermb.presentation.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.victorcs.querosermb.presentation.theme.LocalCustomColors

@Composable
fun ShowErrorMessage(
    errorMessage: String,
    buttonAction: () -> Unit,
    buttonText: String?,
    modifier: Modifier?
) {
    Box(modifier = Modifier.fillMaxSize().padding(8.dp), contentAlignment = Alignment.Center) {
        Column {
            Text(
                text = errorMessage, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = LocalCustomColors.current.exchangeInfo,
                textAlign = TextAlign.Center
            )
            buttonText?.let {
                ActionButton(modifier, buttonAction, it)
            }
        }
    }
}

@Preview
@Composable
fun ShowErrorMessagePreview() {
    ShowErrorMessage("No data available", modifier = Modifier.fillMaxWidth(), buttonText = "Retry", buttonAction = {})
}