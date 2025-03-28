package br.com.victorcs.querosermb.domain.repository

import br.com.victorcs.querosermb.domain.model.Exchange
import br.com.victorcs.querosermb.domain.model.Icon
import br.com.victorcs.querosermb.domain.model.Response

typealias ExchangesResponse = Response<List<Exchange>>
typealias ExchangesIconsResponse = Response<List<Icon>>

interface IExchangesRepository {
    suspend fun getExchanges(): ExchangesResponse
    suspend fun getIcons(): ExchangesIconsResponse
}