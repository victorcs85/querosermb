package br.com.victorcs.querosermb.presentation.exchangedetails.command

sealed class ExchangeDetailsCommand {
    class GetExchangeDetails(val exchangeId: String) : ExchangeDetailsCommand()
}