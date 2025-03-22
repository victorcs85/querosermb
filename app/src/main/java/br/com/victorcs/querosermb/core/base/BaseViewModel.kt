package br.com.victorcs.querosermb.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.victorcs.querosermb.presentation.utils.IDispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import timber.log.Timber

abstract class BaseViewModel(private val dispatchersProvider: IDispatchersProvider) : ViewModel(), KoinComponent {

    protected fun launch(
        coroutineDispatcher: CoroutineDispatcher = dispatchersProvider.io,
        errorBlock: ((Throwable) -> Unit?)? = null,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(coroutineDispatcher) {
        runCatching {
            block()
        }.onFailure { error ->
            Timber.e(error)
            errorBlock?.invoke(error)
        }
    }
}