package br.com.victorcs.querosermb.data.source.remote.mapper

import br.com.victorcs.querosermb.data.source.remote.entity.IconResponse
import br.com.victorcs.querosermb.domain.mapper.DomainMapper
import br.com.victorcs.querosermb.domain.model.Icon

class ExchangeIconMapper : DomainMapper<IconResponse, Icon> {

    override fun toDomain(from: IconResponse): Icon = with(from) {
        Icon(
            exchangeId = exchangeId.orEmpty(),
            assetId = assetId.orEmpty(),
            url = url.orEmpty()
        )
    }
}