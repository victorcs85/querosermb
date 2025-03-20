package br.com.victorcs.querosermb.domain.repository

import br.com.victorcs.querosermb.domain.model.Exchange
import br.com.victorcs.querosermb.domain.model.Response

typealias ExchangesResponse = Response<List<Exchange>>

interface IExchangeRepository {
    suspend fun getExchangeRates(): ExchangesResponse
}