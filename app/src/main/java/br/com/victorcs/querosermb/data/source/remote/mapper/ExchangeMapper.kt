package br.com.victorcs.querosermb.data.source.remote.mapper

import br.com.victorcs.querosermb.core.extensions.toFormatedDate
import br.com.victorcs.querosermb.data.source.remote.entity.ExchangeResponse
import br.com.victorcs.querosermb.domain.mapper.DomainMapper
import br.com.victorcs.querosermb.domain.model.Exchange
import br.com.victorcs.querosermb.domain.model.Icons

class ExchangeMapper : DomainMapper<ExchangeResponse, Exchange> {

    override fun toDomain(from: ExchangeResponse): Exchange {
        return with(from) {
            Exchange(
                exchangeId = exchangeId.orEmpty(),
                website = website.orEmpty(),
                name = name.orEmpty(),
                dataQuoteStart = dataQuoteStart?.toFormatedDate().orEmpty(),
                dataQuoteEnd = dataQuoteEnd?.toFormatedDate().orEmpty(),
                dataOrderbookStart = dataOrderbookStart?.toFormatedDate().orEmpty(),
                dataOrderbookEnd = dataOrderbookEnd?.toFormatedDate().orEmpty(),
                dataTradeStart = dataTradeStart?.toFormatedDate().orEmpty(),
                dataTradeEnd = dataTradeEnd?.toFormatedDate().orEmpty(),
                dataSymbolsCount = dataSymbolsCount?.toString().orEmpty(),
                volume1HrsUsd = volume1HrsUsd ?: 0.0,
                volume1DayUsd = volume1DayUsd ?: 0.0,
                volume1MthUsd = volume1MthUsd ?: 0.0,
                rank = rank ?: 0,
                integrationStatus = integrationStatus.orEmpty(),
                icons = icons?.map {
                    Icons(
                        exchangeId = it.exchangeId.orEmpty(),
                        assetId = it.assetId.orEmpty(),
                        url = it.url.orEmpty()
                    )
                },
                metricId = metricId.orEmpty()
            )
        }
    }
}
