package br.com.victorcs.querosermb.core.extensions

import br.com.victorcs.querosermb.core.constants.UNKNOWN_ERROR
import br.com.victorcs.querosermb.domain.model.Response

fun <T> List<T>.asSuccessResponse(): Response<List<T>> = Response.Success(this)

fun <T> Throwable.asFailureResponse(): Response<T> =
    Response.Error(this.message ?: UNKNOWN_ERROR)

inline fun <T> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
    if (this is Response.Success) {
        action(this.data)
    }
    return this
}

inline fun <T> Response<T>.onFailure(action: (String) -> Unit): Response<T> {
    if (this is Response.Error) {
        action(this.errorMessage)
    }
    return this
}