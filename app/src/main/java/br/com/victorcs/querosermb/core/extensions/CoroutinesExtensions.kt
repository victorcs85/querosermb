package br.com.victorcs.querosermb.core.extensions

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> async(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    func: suspend () -> T
): T {
    return withContext(dispatcher) {
        func.invoke()
    }
}