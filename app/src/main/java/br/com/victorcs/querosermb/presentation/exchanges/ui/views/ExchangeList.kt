package br.com.victorcs.querosermb.presentation.exchanges.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.victorcs.querosermb.domain.model.Exchange
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@Composable
fun ExchangeList(
    exchanges: List<Exchange>,
    navController: NavController,
    listState: LazyListState
) {
    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            count = exchanges.size,
            key = { exchanges[it].exchangeId },
            itemContent = { index ->
                val exchange = exchanges[index]
                ExchangeItem(exchange) {
                    navController.navigate("details/${exchange.exchangeId}")
                }
                if (index < exchanges.lastIndex)
                    HorizontalDivider(color = Color.Black, thickness = 1.dp)
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExchangesPreview() {
    val context = LocalContext.current
    val fakeNavController = remember { NavController(context) }
    val listState = rememberLazyListState()

    ExchangeList(
        getMockExchangeList(),
        fakeNavController,
        listState
    )
}

internal fun getMockExchangeList(): List<Exchange> {
    val json = """[
    {
        "exchange_id": "BINANCE",
        "website": "https://www.binance.com/",
        "name": "Binance",
        "data_quote_start": "2017-12-18T00:00:00.0000000Z",
        "data_quote_end": "2025-03-20T00:00:00.0000000Z",
        "data_orderbook_start": "2017-12-18T00:00:00.0000000Z",
        "data_orderbook_end": "2025-03-20T00:00:00.0000000Z",
        "data_trade_start": "2017-07-14T00:00:00.0000000Z",
        "data_trade_end": "2025-02-24T00:00:00.0000000Z",
        "data_symbols_count": 2991,
        "volume_1hrs_usd": 466638197.85,
        "volume_1day_usd": 7825316101.23,
        "volume_1mth_usd": 585129322561.28,
        "rank": 2,
        "integration_status": "INTEGRATED"
    },
    {
        "exchange_id": "KRAKEN",
        "website": "https://www.kraken.com/",
        "name": "Kraken",
        "data_quote_start": "2014-07-31T00:00:00.0000000Z",
        "data_quote_end": "2025-03-18T00:00:00.0000000Z",
        "data_orderbook_start": "2014-07-31T13:05:46.0000000Z",
        "data_orderbook_end": "2025-03-20T00:00:00.0000000Z",
        "data_trade_start": "2013-10-22T00:00:00.0000000Z",
        "data_trade_end": "2025-02-24T00:00:00.0000000Z",
        "data_symbols_count": 1208,
        "volume_1hrs_usd": 38207848.15,
        "volume_1day_usd": 516595815.17,
        "volume_1mth_usd": 38211429258.52,
        "rank": 2,
        "integration_status": "INTEGRATED"
    },
    {
        "exchange_id": "COINBASE",
        "website": "https://pro.coinbase.com/",
        "name": "Coinbase Pro (GDAX)",
        "data_quote_start": "2015-05-17T00:00:00.0000000Z",
        "data_quote_end": "2025-03-19T00:00:00.0000000Z",
        "data_orderbook_start": "2015-05-17T00:00:00.0000000Z",
        "data_orderbook_end": "2025-03-20T00:00:00.0000000Z",
        "data_trade_start": "2015-01-14T00:00:00.0000000Z",
        "data_trade_end": "2025-02-24T00:00:00.0000000Z",
        "data_symbols_count": 751,
        "volume_1hrs_usd": 57371263.50,
        "volume_1day_usd": 988443038.88,
        "volume_1mth_usd": 114394866849.03,
        "rank": 2,
        "integration_status": "INTEGRATED"
    },
    {
        "exchange_id": "BITSTAMP",
        "website": "https://www.bitstamp.net/",
        "name": "Bitstamp Ltd.",
        "data_quote_start": "2014-02-24T00:00:00.0000000Z",
        "data_quote_end": "2025-03-15T00:00:00.0000000Z",
        "data_orderbook_start": "2014-03-17T00:00:00.0000000Z",
        "data_orderbook_end": "2025-03-20T00:00:00.0000000Z",
        "data_trade_start": "2011-09-13T00:00:00.0000000Z",
        "data_trade_end": "2025-02-24T00:00:00.0000000Z",
        "data_symbols_count": 295,
        "volume_1hrs_usd": 12360744.46,
        "volume_1day_usd": 146021986.30,
        "volume_1mth_usd": 11634981696.42,
        "rank": 2,
        "integration_status": "INTEGRATED"
    },
    {
        "exchange_id": "GEMINI",
        "website": "https://gemini.com/",
        "name": "Gemini",
        "data_quote_start": "2017-03-18T00:00:00.0000000Z",
        "data_quote_end": "2025-03-04T00:00:00.0000000Z",
        "data_orderbook_start": "2017-03-18T00:00:00.0000000Z",
        "data_orderbook_end": "2025-03-20T00:00:00.0000000Z",
        "data_trade_start": "2015-10-08T00:00:00.0000000Z",
        "data_trade_end": "2025-02-24T00:00:00.0000000Z",
        "data_symbols_count": 148,
        "volume_1hrs_usd": 1972054.95,
        "volume_1day_usd": 29331045.67,
        "volume_1mth_usd": 4190743386.96,
        "metric_id": [
            "AUCTION_COLLAR_PRICE",
            "AUCTION_HIGHEST_BID",
            "AUCTION_LOWEST_ASK",
            "AUCTION_PRICE",
            "AUCTION_QUANTITY",
            "AUCTION_RESULT",
            "SYMBOL_DETAILS_MIN_ORDER_SIZE",
            "SYMBOL_DETAILS_QUOTE_INCREMENT",
            "SYMBOL_DETAILS_STATUS",
            "SYMBOL_DETAILS_TICK_SIZE",
            "SYMBOL_DETAILS_WRAP_ENABLED"
        ],
        "rank": 2,
        "integration_status": "INTEGRATED"
    },
    {
        "exchange_id": "ECB",
        "website": "https://www.ecb.europa.eu/",
        "name": "European Central Bank",
        "data_quote_start": "2020-08-31T00:00:00.0000000Z",
        "data_quote_end": "2025-03-20T00:00:00.0000000Z",
        "data_trade_start": "2022-06-06T14:00:00.0000000Z",
        "data_trade_end": "2022-06-10T14:00:00.0000000Z",
        "data_symbols_count": 32,
        "volume_1hrs_usd": 0,
        "volume_1day_usd": 0,
        "volume_1mth_usd": 0,
        "rank": 2,
        "integration_status": "INTEGRATED"
    },
    {
        "exchange_id": "LMAXDIGITAL",
        "website": "https://www.lmaxdigital.com/",
        "name": "LMAX Digital",
        "data_quote_start": "2020-12-03T00:00:00.0000000Z",
        "data_quote_end": "2025-03-04T00:00:00.0000000Z",
        "data_orderbook_start": "2021-04-12T00:00:00.0000000Z",
        "data_orderbook_end": "2025-03-20T00:00:00.0000000Z",
        "data_trade_start": "2020-12-03T00:00:00.0000000Z",
        "data_trade_end": "2025-02-24T00:00:00.0000000Z",
        "data_symbols_count": 16,
        "volume_1hrs_usd": 4233034.00,
        "volume_1day_usd": 75009883.36,
        "volume_1mth_usd": 3940985931.97,
        "rank": 2,
        "integration_status": "PRIVATE_VISIBLE"
    },
    {
        "exchange_id": "DERIBIT",
        "website": "https://www.deribit.com/",
        "name": "Deribit",
        "data_quote_start": "2017-03-14T20:03:36.9946659Z",
        "data_quote_end": "2025-03-20T00:00:00.0000000Z",
        "data_orderbook_start": "2017-03-14T00:00:00.0000000Z",
        "data_orderbook_end": "2025-03-20T00:00:00.0000000Z",
        "data_trade_start": "2016-12-02T00:00:00.0000000Z",
        "data_trade_end": "2025-02-24T00:00:00.0000000Z",
        "data_symbols_count": 257720,
        "volume_1hrs_usd": 14003517335708.67,
        "volume_1day_usd": 327340747132145.61,
        "volume_1mth_usd": 16438819472062321.21,
        "metric_id": [
            "DERIVATIVES_DELIVERY_PRICE",
            "DERIVATIVES_FUNDING_RATE_CURRENT",
            "DERIVATIVES_FUNDING_RATE_NEXT",
            "DERIVATIVES_INDEX_PRICE",
            "DERIVATIVES_INDEX_PRICE",
            "DERIVATIVES_MARK_PRICE",
            "DERIVATIVES_MARK_PRICE_IV",
            "DERIVATIVES_OPEN_INTEREST",
            "DERIVATIVES_SETTLEMENT_PRICE",
            "GREEKS_DELTA",
            "GREEKS_GAMMA",
            "GREEKS_RHO",
            "GREEKS_THETA",
            "GREEKS_VEGA",
            "IV_ASK",
            "IV_BID",
            "IV_INTEREST_RATE",
            "IV_UNDERLYING_PRICE",
            "LIQUIDATION_INDEX_PRICE",
            "LIQUIDATION_IV",
            "LIQUIDATION_MARK_PRICE",
            "LIQUIDATION_POSITION_ID",
            "LIQUIDATION_PRICE",
            "LIQUIDATION_QUANTITY",
            "LIQUIDATION_SIDE",
            "LIQUIDATION_SYMBOL",
            "LIQUIDATION_TICK_DIRECTION",
            "LIQUIDATION_TIME"
        ],
        "rank": 1,
        "integration_status": "INTEGRATED"
    },
    {
        "exchange_id": "OKEX",
        "website": "https://www.okx.com/",
        "name": "OKX",
        "data_quote_start": "2017-12-18T00:00:00.0000000Z",
        "data_quote_end": "2025-03-20T00:00:00.0000000Z",
        "data_orderbook_start": "2017-12-18T00:00:00.0000000Z",
        "data_orderbook_end": "2025-03-20T00:00:00.0000000Z",
        "data_trade_start": "2017-12-01T00:00:00.0000000Z",
        "data_trade_end": "2025-02-24T00:00:00.0000000Z",
        "data_symbols_count": 127784,
        "volume_1hrs_usd": 1501945798680.56,
        "volume_1day_usd": 38230789797812.11,
        "volume_1mth_usd": 3147057297394521.20,
        "metric_id": [
            "DERIVATIVES_FUNDING_RATE_CURRENT",
            "DERIVATIVES_FUNDING_RATE_NEXT",
            "DERIVATIVES_FUNDING_TIME",
            "DERIVATIVES_MARK_PRICE",
            "DERIVATIVES_SETTLEMENT_PRICE",
            "LIQUIDATION_CURRENCY",
            "LIQUIDATION_INSTRUMENT_ID",
            "LIQUIDATION_INSTRUMENT_TYPE",
            "LIQUIDATION_NUMBER_OF_LOSSES",
            "LIQUIDATION_POSITION_SIDE",
            "LIQUIDATION_PRICE",
            "LIQUIDATION_QUANTITY",
            "LIQUIDATION_SIDE",
            "LIQUIDATION_TIME",
            "LIQUIDATION_TOTAL_LOSS"
        ],
        "rank": 1,
        "integration_status": "INTEGRATED"
    }
]"""

    return json.parseJsonToExchangeList()
}

private fun String.parseJsonToExchangeList(): List<Exchange> {
    val moshi = Moshi.Builder().build()
    val listType = Types.newParameterizedType(List::class.java, Exchange::class.java)
    val adapter = moshi.adapter<List<Exchange>>(listType)
    return adapter.fromJson(this) ?: emptyList()
}
