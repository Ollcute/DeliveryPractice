package ru.kit.rediexpress.ui.fragments.login.signUp

import androidx.lifecycle.viewModelScope
import ru.kit.rediexpress.domain.interactors.AuthInteractor
import ru.kit.rediexpress.domain.onError
import ru.kit.rediexpress.domain.onSuccess
import ru.kit.rediexpress.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import presentation.utils.EventProvider
import presentation.utils.impl.EventProviderImpl

class SignUpViewModel(
    private val authInteractor: AuthInteractor
): BaseViewModel(), EventProvider<SignUpEvent> by EventProviderImpl() {

    fun signUp(
        fullname: String,
        phone: String,
        email: String,
        password: String,
        repeatPassword: String
    ) = viewModelScope.launch {
        authInteractor.createAccount(
            fullname, phone, email, password, repeatPassword
        ).onSuccess {
            viewModelScope.launch {
                emitEvent(event = SignUpEvent.OnSuccessSignUp)
            }
        }.onError {error ->
            onError(message = error.details)
        }
    }
}