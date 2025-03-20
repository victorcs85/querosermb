package br.com.victorcs.querosermb.presentation.exchanges.ui.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.victorcs.querosermb.R

@Composable
fun ShowEmptyList() {
    Text(stringResource(R.string.no_data_available))
}

@Preview
@Composable
fun ShowEmptyListPreview() {
    Text(stringResource(R.string.no_data_available))
}