package br.com.victorcs.querosermb.presentation.exchangedetails.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.victorcs.querosermb.R
import br.com.victorcs.querosermb.core.constants.EXCHANGE_ID
import br.com.victorcs.querosermb.domain.model.Exchange
import br.com.victorcs.querosermb.presentation.exchangedetails.command.ExchangeDetailsCommand
import br.com.victorcs.querosermb.presentation.views.ShowErrorMessage
import br.com.victorcs.querosermb.presentation.views.ShowLoading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeDetailScreen(navController: NavController, viewModel: ExchangeDetailsViewModel) {
    val state = viewModel.state.collectAsState().value
    val exchange = state.exchange

    if (exchange == null && !state.isLoading) {
        val exchangeId = navController.previousBackStackEntry?.arguments?.getString(EXCHANGE_ID)
        exchangeId?.let {
            viewModel.execute(ExchangeDetailsCommand.GetExchangeDetails(it))
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                },
                title = {
                    Text(
                        text = exchange?.name ?: stringResource(R.string.exchange_details_title)
                    )
                }
            )
        }
    ) { contentPadding ->
        when {
            state.isLoading -> ShowLoading()
            state.errorMessage != null -> ShowErrorMessage(state.errorMessage)
            exchange != null -> DetailsContent(contentPadding, exchange)
        }
    }
}

@Composable
private fun DetailsContent(
    contentPadding: PaddingValues,
    exchange: Exchange?
) {
    Box(modifier = Modifier.padding(contentPadding)) {

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(R.string.exchange_details),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(alpha = 0.4f)),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(stringResource(R.string.website, exchange?.website.orEmpty()))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        stringResource(
                            R.string.data_quote_start,
                            exchange?.dataQuoteStart.orEmpty()
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(stringResource(R.string.data_quote_end, exchange?.dataQuoteEnd.orEmpty()))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        stringResource(
                            R.string.volume_one_hour,
                            exchange?.volume1HrsUsd ?: 0.0
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(stringResource(R.string.volume_one_day, exchange?.volume1DayUsd ?: 0.0))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(stringResource(R.string.volume_one_month, exchange?.volume1MthUsd ?: 0.0))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(stringResource(R.string.rank, exchange?.rank ?: 0))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        stringResource(
                            R.string.integration_status,
                            exchange?.integrationStatus ?: 0
                        )
                    )
                }
            }
        }
    }
}
