package br.com.victorcs.querosermb.presentation.exchanges.ui

import androidx.lifecycle.viewModelScope
import br.com.victorcs.querosermb.core.base.BaseViewModel
import br.com.victorcs.querosermb.core.constants.ERROR_MESSAGE
import br.com.victorcs.querosermb.core.constants.NETWORK_ERROR
import br.com.victorcs.querosermb.domain.model.Exchange
import br.com.victorcs.querosermb.domain.model.Response
import br.com.victorcs.querosermb.domain.repository.ExchangesResponse
import br.com.victorcs.querosermb.domain.repository.IExchangesRepository
import br.com.victorcs.querosermb.presentation.exchanges.command.ExchangesCommand
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber

class ExchangesViewModel(
    private val repository: IExchangesRepository
) : BaseViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    private val _state = MutableStateFlow<ExchangesResponse>(Response.Idle)

    val screenState: StateFlow<ExchangesScreenState> = _state
        .combine(_isRefreshing) { state, isRefreshing ->
            ExchangesScreenState(
                exchanges = (state as? Response.Success)?.data ?: emptyList(),
                isRefreshing = isRefreshing,
                isLoading = state is Response.Loading,
                errorMessage = if(state is Response.Error && state.errorMessage.contains(
                        NETWORK_ERROR)) {
                    NETWORK_ERROR } else (state as? Response.Error)?.errorMessage
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ExchangesScreenState()
        )

    fun execute(command: ExchangesCommand) = when (command) {
        is ExchangesCommand.FetchExchanges -> fetchExchanges()
        is ExchangesCommand.RefreshExchanges -> refreshExchanges()
    }

    private fun fetchExchanges() {
        launch {
            try {
                _state.value = Response.Loading
                val exchanges = repository.getExchanges()
                _state.value = exchanges
            } catch (e: Exception) {
                Timber.e(e)
                _state.value = Response.Error(ERROR_MESSAGE)
            } finally {
                _isRefreshing.value = false
            }
        }
    }

    private fun refreshExchanges() {
        _isRefreshing.value = true
        fetchExchanges()
    }
}

data class ExchangesScreenState(
    val exchanges: List<Exchange>? = null,
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)