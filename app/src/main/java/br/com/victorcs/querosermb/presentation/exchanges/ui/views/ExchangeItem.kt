package br.com.victorcs.querosermb.presentation.exchanges.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.victorcs.querosermb.R
import br.com.victorcs.querosermb.domain.model.Exchange
import br.com.victorcs.querosermb.presentation.theme.LocalCustomColors
import coil.compose.rememberAsyncImagePainter

@Composable
fun ExchangeItem(exchange: Exchange, onClick: () -> Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = exchange.icons?.firstOrNull()?.url?.let { rememberAsyncImagePainter(it) } ?: painterResource(id = R.drawable.logo_mb_icon_color),
            contentDescription = null,
            modifier = Modifier.size(48.dp).padding(8.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(text = exchange.name, fontSize= 16.sp, fontWeight = FontWeight.Bold, color = LocalCustomColors.current.exchangeTitle)
            Text(text = stringResource(R.string.exchange_id, exchange.exchangeId), fontSize = 14.sp, color = LocalCustomColors.current.exchangeInfo)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.volume_one_day_usd, exchange.volume1DayUsd),
                style = MaterialTheme.typography.bodyLarge, color = LocalCustomColors.current.exchangeVolume)
        }
        Image(
            painter = painterResource(R.drawable.chevron_right_24px),
            contentDescription = null,
            modifier = Modifier.size(32.dp),
        )
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