package br.com.victorcs.querosermb.shared.test

import br.com.victorcs.querosermb.domain.model.Exchange
import br.com.victorcs.querosermb.domain.model.Response

object PresentationMockTest {

    private val mockExchangeList = listOf(
        Exchange(
            exchangeId = "BINANCE",
            website = "https://www.binance.com/",
            name = "Binance",
            dataQuoteStart = "2017-12-18",
            dataQuoteEnd = "2025-03-21",
            dataOrderbookStart = "2017-12-18",
            dataOrderbookEnd = "2025-03-21",
            dataTradeStart = "2017-07-14",
            dataTradeEnd = "2025-02-24",
            dataSymbolsCount = "2991",
            volume1HrsUsd = 0.0,
            volume1DayUsd = 0.0,
            volume1MthUsd = 0.0,
            rank = 2,
            integrationStatus = "INTEGRATED",
            metricId = emptyList()
        ),
        Exchange(
            exchangeId = "KRAKEN",
            website = "https://www.kraken.com/",
            name = "Kraken",
            dataQuoteStart = "2014-07-31",
            dataQuoteEnd = "2025-03-18",
            dataOrderbookStart = "2014-07-31",
            dataOrderbookEnd = "2025-03-21",
            dataTradeStart = "2013-10-22",
            dataTradeEnd = "2025-02-24",
            dataSymbolsCount = "1208",
            volume1HrsUsd = 0.0,
            volume1DayUsd = 0.0,
            volume1MthUsd = 0.0,
            rank = 2,
            integrationStatus = "INTEGRATED",
            metricId = emptyList()
        )
    )

    val mockSuccessExchangeResponse: Response<List<Exchange>> = Response.Success(mockExchangeList)

    private val mockExchangeRefreshList = listOf(
        Exchange(
            exchangeId = "COINBASE",
            website = "https://pro.coinbase.com/",
            name = "Coinbase Pro (GDAX)",
            dataQuoteStart = "2015-05-17",
            dataQuoteEnd = "2025-03-22",
            dataOrderbookStart = "2015-05-17",
            dataOrderbookEnd = "2025-03-22",
            dataTradeStart = "2015-01-14",
            dataTradeEnd = "2025-02-24",
            dataSymbolsCount = "751",
            volume1HrsUsd = 28035079.74,
            volume1DayUsd = 739625976.23,
            volume1MthUsd = 113823711648.39,
            rank = 2,
            integrationStatus = "INTEGRATED",
            metricId = emptyList()
        ),
        Exchange(
            exchangeId = "BITSTAMP",
            website = "https://www.bitstamp.net/",
            name = "Bitstamp Ltd.",
            dataQuoteStart = "2014-02-24",
            dataQuoteEnd = "2025-03-22",
            dataOrderbookStart = "2014-03-17",
            dataOrderbookEnd = "2025-03-22",
            dataTradeStart = "2011-09-13",
            dataTradeEnd = "2025-02-24",
            dataSymbolsCount = "295",
            volume1HrsUsd = 1399656.72,
            volume1DayUsd = 44298156.68,
            volume1MthUsd = 11613360311.66,
            rank = 2,
            integrationStatus = "INTEGRATED",
            metricId = emptyList()
        )
    )

    val mockSuccessExchangeRefreshResponse: Response<List<Exchange>> = Response.Success(
        mockExchangeRefreshList
    )

    val mockSuccessExchangeDetailsResponse: Response<List<Exchange>> = Response.Success(listOf(mockExchangeRefreshList.first()))

    const val BINANCE = "Binance"
    const val COINBASE = "Coinbase Pro (GDAX)"
}