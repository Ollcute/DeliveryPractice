package ru.kit.rediexpress.domain.interactors

import ru.kit.rediexpress.domain.DomainResult
import io.github.jan.supabase.gotrue.SessionStatus
import kotlinx.coroutines.flow.SharedFlow

interface AuthInteractor {
    val sessionStatus: SharedFlow<SessionStatus>

    suspend fun createAccount(
        fullname: String,
        phone: String,
        email: String,
        password: String,
        repeatPassword: String
    ): DomainResult<Boolean>

    suspend fun login(
        email: String,
        password: String
    )
}