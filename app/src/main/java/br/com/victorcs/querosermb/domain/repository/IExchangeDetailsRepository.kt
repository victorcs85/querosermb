package br.com.victorcs.querosermb.domain.repository

interface IExchangeDetailsRepository {
    suspend fun getExchangeDetails(exchangeId: String): ExchangesResponse
}