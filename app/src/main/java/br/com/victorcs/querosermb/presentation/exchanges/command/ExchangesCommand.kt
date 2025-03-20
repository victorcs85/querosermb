package br.com.victorcs.querosermb.presentation.exchanges.command

sealed class ExchangesCommand {
    object FetchExchanges : ExchangesCommand()
    object RefreshExchanges : ExchangesCommand()
}