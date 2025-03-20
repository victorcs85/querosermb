package br.com.victorcs.querosermb.data.repository

import br.com.victorcs.querosermb.domain.model.Exchange
import br.com.victorcs.querosermb.domain.model.Response

interface IExchangeRepository {
    suspend fun getExchangeRates(): Response<Exchange>
}