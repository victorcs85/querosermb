package br.com.victorcs.querosermb.data.source.remote.repository

import br.com.victorcs.querosermb.data.source.remote.CoinAPI
import br.com.victorcs.querosermb.data.source.remote.entity.ExchangeResponse
import br.com.victorcs.querosermb.domain.mapper.DomainMapper
import br.com.victorcs.querosermb.domain.model.Exchange
import br.com.victorcs.querosermb.domain.model.Response
import br.com.victorcs.querosermb.domain.repository.ExchangesResponse
import br.com.victorcs.querosermb.domain.repository.IExchangeDetailsRepository
import timber.log.Timber

class ExchangeDetailsRepositoryImpl(
    private val service: CoinAPI,
    private val mapper: DomainMapper<ExchangeResponse, Exchange>
): IExchangeDetailsRepository {
    override suspend fun getExchangeDetails(exchangeId: String): ExchangesResponse = try {
        val response = service.getExchange(exchangeId)
        Response.Success(
            mapper.toDomain(response)
        )
    } catch (e: Exception) {
        Timber.e(e)
        Response.Error(e.toString())
    }
}