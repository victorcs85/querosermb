package br.com.victorcs.querosermb.data.source.remote.repository

import br.com.victorcs.querosermb.domain.repository.IExchangeRepository
import br.com.victorcs.querosermb.data.source.remote.CoinAPI
import br.com.victorcs.querosermb.data.source.remote.entity.ExchangeResponse
import br.com.victorcs.querosermb.domain.mapper.DomainMapper
import br.com.victorcs.querosermb.domain.model.Exchange
import br.com.victorcs.querosermb.domain.model.Response
import br.com.victorcs.querosermb.domain.repository.ExchangesResponse

class ExchangeRepositoryImpl(
    private val service: CoinAPI,
    private val mapper: DomainMapper<ExchangeResponse, Exchange>
): IExchangeRepository {
    override suspend fun getExchangeRates(): ExchangesResponse = try {
        val response = service.getExchanges()
        Response.Success(
            mapper.toDomain(response)
        )
    } catch (e: Exception) {
        Response.Error(e.toString())
    }
}