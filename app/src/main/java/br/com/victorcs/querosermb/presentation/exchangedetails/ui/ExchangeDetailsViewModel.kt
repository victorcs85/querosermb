package br.com.victorcs.querosermb.presentation.exchangedetails.ui

import androidx.compose.runtime.Immutable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import br.com.victorcs.querosermb.core.base.BaseViewModel
import br.com.victorcs.querosermb.core.constants.ERROR_MESSAGE
import br.com.victorcs.querosermb.core.constants.EXCHANGE_ID
import br.com.victorcs.querosermb.core.constants.STOP_TIMER_LIMIT
import br.com.victorcs.querosermb.domain.model.Exchange
import br.com.victorcs.querosermb.domain.model.Response
import br.com.victorcs.querosermb.domain.repository.IExchangeDetailsRepository
import br.com.victorcs.querosermb.presentation.exchangedetails.command.ExchangeDetailsCommand
import br.com.victorcs.querosermb.presentation.utils.IDispatchersProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import timber.log.Timber

class ExchangeDetailsViewModel(
    private val repository: IExchangeDetailsRepository,
    private val savedStateHandle: SavedStateHandle,
    dispatchers: IDispatchersProvider
) : BaseViewModel(dispatchers) {

    private val _state = MutableStateFlow(ExchangeDetailsScreenState())
    val screenState: StateFlow<ExchangeDetailsScreenState> = _state
        .onStart {
            savedStateHandle.get<String>(EXCHANGE_ID)?.let { exchangeId ->
                if (_state.value.exchange == null) {
                    getExchangeDetails(exchangeId)
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMER_LIMIT),
            initialValue = ExchangeDetailsScreenState().copy(isLoading = true)
        )

    fun execute(command: ExchangeDetailsCommand) = when (command) {
        is ExchangeDetailsCommand.GetExchangeDetails -> getExchangeDetails(command.exchangeId)
    }

    private fun getExchangeDetails(exchangeId: String) {
        launch {
            _state.update { currentState ->
                currentState.copy(isLoading = true)
            }
            try {
                val exchangeResponse = repository.getExchangeDetails(exchangeId)

                if (exchangeResponse is Response.Success && exchangeResponse.data.isNotEmpty()) {
                    val exchange = exchangeResponse.data.first()
                    _state.update { currentState ->
                        currentState.copy(
                            exchange = exchange,
                            isLoading = false
                        )
                    }
                    savedStateHandle[EXCHANGE_ID] = exchangeId
                } else {
                    _state.update { currentState ->
                        currentState.copy(
                            errorMessage = ERROR_MESSAGE
                        )
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
                _state.update { currentState ->
                    currentState.copy(
                        errorMessage = ERROR_MESSAGE
                    )
                }
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