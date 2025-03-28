package br.com.victorcs.querosermb.presentation.exchangedetails.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.victorcs.querosermb.R
import br.com.victorcs.querosermb.core.constants.EXCHANGE_ICON
import br.com.victorcs.querosermb.core.constants.EXCHANGE_ID
import br.com.victorcs.querosermb.domain.model.Exchange
import br.com.victorcs.querosermb.domain.model.Icon
import br.com.victorcs.querosermb.presentation.exchangedetails.command.ExchangeDetailsCommand
import br.com.victorcs.querosermb.presentation.theme.LocalCustomColors
import br.com.victorcs.querosermb.presentation.views.ExchangeTopAppBar
import br.com.victorcs.querosermb.presentation.views.LoadingView
import br.com.victorcs.querosermb.presentation.views.ShowErrorMessage
import coil.compose.rememberAsyncImagePainter

@Composable
fun ExchangeDetailScreen(
    navController: NavController,
    state: ExchangeDetailsScreenState,
    execute: (ExchangeDetailsCommand) -> Unit
) {
    val exchange = state.exchange
    val exchangeId = rememberSaveable() {
        navController.previousBackStackEntry?.arguments?.getString(EXCHANGE_ID).orEmpty()
    }

    val navBackStackEntry = navController.previousBackStackEntry
    val icon = navBackStackEntry?.savedStateHandle?.get<Icon>(EXCHANGE_ICON)

    Scaffold(
        topBar = {
            ExchangeTopAppBar(
                title = exchange?.name ?: stringResource(R.string.exchange_details_title),
                onBackPressed = { navController.popBackStack() }
            )
        }
    ) { contentPadding ->
        when {
            state.isLoading -> LoadingView()
            state.errorMessage != null -> ShowErrorMessage(
                state.errorMessage, buttonText = stringResource(R.string.reload),
                buttonAction = {
                    execute(
                        ExchangeDetailsCommand.GetExchangeDetails(
                            exchangeId
                        )
                    )
                },
                modifier = null
            )

            exchange != null -> DetailsContent(contentPadding, exchange, icon)
        }
    }
}

@Composable
private fun DetailsContent(contentPadding: PaddingValues, exchange: Exchange, icon: Icon?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(16.dp)
    ) {
        icon?.url?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(16f / 9f)
                    .alpha(0.7f)
                    .align(Alignment.TopCenter),
            )
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(R.string.exchange_details),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = LocalCustomColors.current.exchangeDetailsTitle,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.7f))
                    .padding(16.dp)
            ) {
                Column {
                    listOf(
                        R.string.website to exchange.website,
                        R.string.data_quote_start to exchange.dataQuoteStart,
                        R.string.data_quote_end to exchange.dataQuoteEnd,
                        R.string.volume_one_hour to "${exchange.volume1HrsUsd} USD",
                        R.string.volume_one_day to "${exchange.volume1DayUsd} USD",
                        R.string.volume_one_month to "${exchange.volume1MthUsd} USD",
                        R.string.rank to exchange.rank.toString(),
                        R.string.integration_status to exchange.integrationStatus
                    ).forEach { (resId, value) ->
                        Text(
                            text = stringResource(resId),
                            fontWeight = FontWeight.Bold,
                            color = LocalCustomColors.current.exchangeInfo
                        )
                        Text(
                            text = value,
                            modifier = Modifier.padding(bottom = 8.dp),
                            color = LocalCustomColors.current.exchangeInfo
                        )
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = LocalCustomColors.current.divider
                        )
                    }
                }
            }
        }
    }
}