package br.com.victorcs.querosermb.data.source.remote

import br.com.victorcs.querosermb.data.source.remote.entity.ExchangeResponse
import retrofit2.http.GET

interface CoinAPI {
    @GET("v1/exchanges")
    suspend fun getExchanges(): List<ExchangeResponse>
}