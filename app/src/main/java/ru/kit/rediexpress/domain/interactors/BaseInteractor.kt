package ru.kit.rediexpress.domain.interactors

import ru.kit.rediexpress.data.model.DataResult
import ru.kit.rediexpress.domain.DomainResult

abstract class BaseInteractor {

    protected inline fun <reified T> returnDomainResult(noinline resultFunc: () -> DataResult<T>): DomainResult<T> {
        val result = resultFunc()

        return if (result is DataResult.Success<*>) {
            DomainResult.Success(result.data as T)
        } else {
            DomainResult.Error((result as DataResult.Error).errorType, result.errors)
        }
    }
}