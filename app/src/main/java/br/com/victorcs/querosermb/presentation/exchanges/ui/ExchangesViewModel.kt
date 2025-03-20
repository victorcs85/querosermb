package br.com.victorcs.querosermb.presentation.exchanges.ui

import br.com.victorcs.querosermb.core.base.BaseViewModel
import br.com.victorcs.querosermb.domain.model.Response
import br.com.victorcs.querosermb.domain.repository.ExchangesResponse
import br.com.victorcs.querosermb.domain.repository.IExchangeRepository
import br.com.victorcs.querosermb.presentation.exchanges.command.ExchangesCommand
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

private const val ERROR_MESSAGE = "Ocorreu um erro ao buscar os dados!"

class ExchangesViewModel(
    private val repository: IExchangeRepository
) : BaseViewModel() {

    private val _state = MutableStateFlow<ExchangesResponse>(Response.Loading)
    val state: StateFlow<ExchangesResponse> = _state

    fun execute(command: ExchangesCommand) = when (command) {
        is ExchangesCommand.FetchExchanges -> fetchExchanges()
    }

    private fun fetchExchanges() = launch {
        _state.value = Response.Loading
        try {
            val exchanges = repository.getExchangeRates()
            _state.value = exchanges
        } catch (e: Exception) {
            Timber.e(e)
            _state.value = Response.Error(ERROR_MESSAGE)
        }
    }

}