package br.com.victorcs.querosermb.presentation.exchanges.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.victorcs.querosermb.R
import br.com.victorcs.querosermb.domain.model.Exchange
import br.com.victorcs.querosermb.domain.model.Response
import br.com.victorcs.querosermb.presentation.exchanges.command.ExchangesCommand
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExchangesScreen(navController: NavController, viewModel: ExchangesViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit)
    {
        viewModel.execute(ExchangesCommand.FetchExchanges)
    }

    Scaffold { contentPadding ->
        when (state.value) {
            is Response.Idle -> {}
            is Response.Loading -> CircularProgressIndicator()
            is Response.Error -> Text(
                (state.value as Response.Error).errorMessage
            )
            is Response.Success -> {
                val exchanges = (state.value as Response.Success).data
                LazyColumn(
                    contentPadding = contentPadding,
                ) {
                    items(
                        count = exchanges.size,
                        key = { exchanges[it].exchangeId },
                        itemContent = { index ->
                            val exchange = exchanges[index]
                            ExchangeItem(exchange) {
                                navController.navigate("details/${exchange.exchangeId}")
                            }
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun ExchangeItem(exchange: Exchange, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = exchange.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
            Text(text = stringResource(R.string.exchange_id, exchange.exchangeId), fontSize = 14.sp)
            Text(
                text = stringResource(R.string.volume_one_day_usd, exchange.volume1DayUsd),
                fontSize = 12.sp,
                color = Color.Yellow,
            )
        }
    }
}