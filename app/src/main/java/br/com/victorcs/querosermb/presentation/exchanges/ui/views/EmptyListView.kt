package br.com.victorcs.querosermb.presentation.exchanges.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.victorcs.querosermb.R
import br.com.victorcs.querosermb.presentation.theme.LocalCustomColors
import br.com.victorcs.querosermb.presentation.views.ActionButton

@Composable
fun EmptyListView(buttonAction: () -> Unit, buttonText: String?, modifier: Modifier?) {
    Box(modifier = Modifier.fillMaxSize().padding(8.dp), contentAlignment = Alignment.Center) {
        Column {
            Text(
                text = stringResource(R.string.no_data_available),
                modifier = Modifier
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
fun ShowEmptyListPreview() {
    EmptyListView(modifier = Modifier.fillMaxWidth(), buttonAction = {}, buttonText = "Retry")
}