package ru.kit.rediexpress.ui.fragments.login.signIn

sealed class SignInEvent {
    object OnSuccessSignIn: SignInEvent()
}
