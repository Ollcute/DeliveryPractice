package ru.kit.rediexpress.domain

import ru.kit.rediexpress.data.model.ErrorType

sealed class DomainResult<out T> {
    data class Success<T>(val data: T) : DomainResult<T>()
    data class Error(val type: ErrorType, val details: String) : DomainResult<Nothing>()
    data class Loading(val state: LoadingState) : DomainResult<Nothing>()
}

enum class LoadingState {
    INITIAL,
    REQUEST_LOADING
}

fun <T> DomainResult<T>.onSuccess(success: (result: T) -> Unit): DomainResult<T> {
    if (this is DomainResult.Success)
        success(this.data)

    return this
}

fun <T> DomainResult<T>.onError(error: (error: DomainResult.Error) -> Unit): DomainResult<T> {
    if (this is DomainResult.Error)
        error(this)

    return this
}

fun <T> DomainResult<T>.onError(error: (type: ErrorType, details: String) -> Unit): DomainResult<T> {
    if (this is DomainResult.Error)
        error(type, details)

    return this
}