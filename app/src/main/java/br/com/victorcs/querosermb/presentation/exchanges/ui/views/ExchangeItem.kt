package br.com.victorcs.querosermb.presentation.exchanges.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.victorcs.querosermb.R
import br.com.victorcs.querosermb.domain.model.Exchange

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
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Blue
            )
            Text(
                text = stringResource(R.string.exchange_id, exchange.exchangeId),
                fontSize = 14.sp,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(R.string.volume_one_day_usd, exchange.volume1DayUsd),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.DarkGray,
            )
        }
    }
}

@Preview
@Composable
fun ExchangeItemPreview() {
    ExchangeItem(
        exchange = getMockExchangeList().first(),
        onClick = {}
    )
}