package ru.kit.rediexpress.ui.fragments.login.signIn

import androidx.lifecycle.viewModelScope
import ru.kit.rediexpress.domain.DomainResult
import ru.kit.rediexpress.domain.interactors.AuthInteractor
import ru.kit.rediexpress.domain.onSuccess
import ru.kit.rediexpress.domain.supabase.supabase
import ru.kit.rediexpress.ui.base.BaseViewModel
import ru.kit.rediexpress.ui.fragments.login.signUp.SignUpEvent
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.launch
import presentation.utils.EventProvider
import presentation.utils.impl.EventProviderImpl

class SignInViewModel(
    private val authInteractor: AuthInteractor
): BaseViewModel(), EventProvider<SignInEvent> by EventProviderImpl() {
    init {
        viewModelScope.launch {
            authInteractor.sessionStatus.collect {
                when(it) {
                    is SessionStatus.Authenticated -> emitEvent(SignInEvent.OnSuccessSignIn)
                    SessionStatus.LoadingFromStorage -> println("Loading from storage")
                    SessionStatus.NetworkError -> println("Network error")
                    SessionStatus.NotAuthenticated -> println("Not authenticated")
                }
            }
        }
    }

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        authInteractor.login(
            email, password
        )
    }
}