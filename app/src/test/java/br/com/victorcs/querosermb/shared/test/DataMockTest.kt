package br.com.victorcs.querosermb.shared.test

import br.com.victorcs.querosermb.domain.model.Exchange
import br.com.victorcs.querosermb.domain.model.Response

object DataMockTest {
    val mockExchangeList = listOf(
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

    val mockExchangeDetails = listOf(
        mockExchangeList.first()
    )

    val mockSuccessExchangeDetailsResponse: Response<List<Exchange>> = Response.Success(mockExchangeDetails)

    const val DEFAULT_ERROR_MOCK = "Ocorreu um erro ao buscar os dados!"
}