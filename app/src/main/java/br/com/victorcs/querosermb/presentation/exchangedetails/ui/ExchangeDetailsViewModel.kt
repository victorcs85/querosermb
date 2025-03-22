package br.com.victorcs.querosermb.presentation.exchangedetails.ui

import androidx.compose.runtime.Immutable
import br.com.victorcs.querosermb.core.base.BaseViewModel
import br.com.victorcs.querosermb.core.constants.ERROR_MESSAGE
import br.com.victorcs.querosermb.domain.model.Exchange
import br.com.victorcs.querosermb.domain.model.Response
import br.com.victorcs.querosermb.domain.repository.IExchangeDetailsRepository
import br.com.victorcs.querosermb.presentation.exchangedetails.command.ExchangeDetailsCommand
import br.com.victorcs.querosermb.presentation.utils.IDispatchersProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class ExchangeDetailsViewModel(
    private val repository: IExchangeDetailsRepository,
    dispatchers: IDispatchersProvider
) : BaseViewModel(dispatchers) {

    private val _state = MutableStateFlow(ExchangeDetailsScreenState())
    val state: StateFlow<ExchangeDetailsScreenState> = _state

    fun execute(command: ExchangeDetailsCommand) = when (command) {
        is ExchangeDetailsCommand.GetExchangeDetails -> getExchangeDetails(command.exchangeId)
    }

    private fun getExchangeDetails(exchangeId: String) {
        launch {
            _state.value = ExchangeDetailsScreenState(isLoading = true)
            try {
                val exchangeResponse = repository.getExchangeDetails(exchangeId)

                if (exchangeResponse is Response.Success && exchangeResponse.data.isNotEmpty()) {
                    val exchange = exchangeResponse.data.first()
                    _state.value = ExchangeDetailsScreenState(exchange = exchange)
                } else {
                    _state.value = ExchangeDetailsScreenState(errorMessage = ERROR_MESSAGE)
                }
            } catch (e: Exception) {
                Timber.e(e)
                _state.value = ExchangeDetailsScreenState(errorMessage = ERROR_MESSAGE)
            }
        }
    }
}

@Immutable
data class ExchangeDetailsScreenState(
    val exchange: Exchange? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)