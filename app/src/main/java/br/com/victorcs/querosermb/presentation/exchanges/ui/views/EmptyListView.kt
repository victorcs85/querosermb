package br.com.victorcs.querosermb.presentation.exchanges.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.victorcs.querosermb.R
import br.com.victorcs.querosermb.presentation.theme.LocalCustomColors

@Composable
fun EmptyListView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(R.string.no_data_available),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = LocalCustomColors.current.exchangeInfo
        )
    }
}

@Preview
@Composable
fun ShowEmptyListPreview() {
    Text(stringResource(R.string.no_data_available))
}