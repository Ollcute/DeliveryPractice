    package ru.kit.rediexpress.data.model

sealed class DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>()
    data class Error(val errorType: ErrorType, val errors: String = "") : DataResult<Nothing>()
}

enum class ErrorType {
    NO_INTERNET,
    REQUEST_ERROR
}
