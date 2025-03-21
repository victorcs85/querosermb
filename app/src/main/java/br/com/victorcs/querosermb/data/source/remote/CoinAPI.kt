package br.com.victorcs.querosermb.data.source.remote

import br.com.victorcs.querosermb.data.source.remote.entity.ExchangeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinAPI {
    @GET("v1/exchanges")
    suspend fun getExchanges(): List<ExchangeResponse>

    @GET("v1/exchanges/{exchange_id}")
    suspend fun getExchange(@Path("exchange_id") exchangeId: String): List<ExchangeResponse>
}