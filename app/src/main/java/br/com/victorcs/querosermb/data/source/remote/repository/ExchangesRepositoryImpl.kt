package br.com.victorcs.querosermb.data.source.remote.repository

import br.com.victorcs.querosermb.core.constants.ERROR_MESSAGE
import br.com.victorcs.querosermb.data.source.remote.CoinAPI
import br.com.victorcs.querosermb.data.source.remote.entity.ExchangeResponse
import br.com.victorcs.querosermb.data.source.remote.entity.IconResponse
import br.com.victorcs.querosermb.domain.mapper.DomainMapper
import br.com.victorcs.querosermb.domain.model.Exchange
import br.com.victorcs.querosermb.domain.model.Icon
import br.com.victorcs.querosermb.domain.model.Response
import br.com.victorcs.querosermb.domain.repository.ExchangesIconsResponse
import br.com.victorcs.querosermb.domain.repository.ExchangesResponse
import br.com.victorcs.querosermb.domain.repository.IExchangesRepository
import timber.log.Timber

class ExchangesRepositoryImpl(
    private val service: CoinAPI,
    private val mapper: DomainMapper<ExchangeResponse, Exchange>,
    private val iconMapper: DomainMapper<IconResponse, Icon>
) : IExchangesRepository {
    override suspend fun getExchanges(): ExchangesResponse = try {
        val response = service.getExchanges()
        Response.Success(
            mapper.toDomain(response)
        )
    } catch (e: Exception) {
        Timber.e(e)
        Response.Error(ERROR_MESSAGE)
    }

    override suspend fun getIcons(): ExchangesIconsResponse = try {
        val response = service.getExchangesIcons()
        Response.Success(
            iconMapper.toDomain(response)
        )
    } catch (e: Exception) {
        Timber.e(e)
        Response.Error(ERROR_MESSAGE)
    }
}