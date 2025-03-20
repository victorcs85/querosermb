package br.com.victorcs.querosermb.data.source.remote.mapper

import br.com.victorcs.querosermb.core.constants.EMPTY
import br.com.victorcs.querosermb.data.source.remote.entity.ExchangeResponse
import br.com.victorcs.querosermb.domain.mapper.DomainMapper
import br.com.victorcs.querosermb.domain.model.Exchange
import java.text.SimpleDateFormat
import java.util.Locale

private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'"
private const val OUTPUT_DATE_FORMAT = "dd/MM/yyyy"

class ExchangeMapper : DomainMapper<ExchangeResponse, Exchange> {

    private val inputDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    private val outputDateFormat = SimpleDateFormat(OUTPUT_DATE_FORMAT, Locale.getDefault())

    private fun formatDate(date: String?): String {
        return date?.let {
            try {
                inputDateFormat.parse(it)?.let { parsedDate ->
                    outputDateFormat.format(parsedDate)
                }
            } catch (e: Exception) {
                null
            }
        } ?: EMPTY
    }

    override fun toDomain(from: ExchangeResponse): Exchange {
        return Exchange(
            exchangeId = from.exchangeId.orEmpty(),
            website = from.website.orEmpty(),
            name = from.name.orEmpty(),
            dataQuoteStart = formatDate(from.dataQuoteStart),
            dataQuoteEnd = formatDate(from.dataQuoteEnd),
            dataOrderbookStart = formatDate(from.dataOrderbookStart),
            dataOrderbookEnd = formatDate(from.dataOrderbookEnd),
            dataTradeStart = formatDate(from.dataTradeStart),
            dataTradeEnd = formatDate(from.dataTradeEnd),
            dataSymbolsCount = from.dataSymbolsCount?.toString().orEmpty(),
            volume1HrsUsd = from.volume1HrsUsd?.toString().orEmpty(),
            volume1DayUsd = from.volume1DayUsd?.toInt() ?: 0,
            volume1MthUsd = from.volume1MthUsd ?: 0.0,
            rank = from.rank?.toDouble() ?: 0.0,
            integrationStatus = from.integrationStatus?.toDouble() ?: 0.0,
            metricId = from.metricId.orEmpty()
        )
    }
}
