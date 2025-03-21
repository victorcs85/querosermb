package br.com.victorcs.querosermb.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Exchange(
    val exchangeId: String,
    val website: String,
    val name: String,
    val dataQuoteStart: String,
    val dataQuoteEnd: String,
    val dataOrderbookStart: String,
    val dataOrderbookEnd: String,
    val dataTradeStart: String,
    val dataTradeEnd: String,
    val dataSymbolsCount: String,
    val volume1HrsUsd: String,
    val volume1DayUsd: Int,
    val volume1MthUsd: Double,
    val rank: Int,
    val integrationStatus: String,
    val metricId: List<String>
) : Parcelable
