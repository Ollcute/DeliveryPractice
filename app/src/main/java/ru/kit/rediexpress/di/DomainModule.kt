package ru.kit.rediexpress.di

import ru.kit.rediexpress.domain.interactors.AuthInteractor
import ru.kit.rediexpress.domain.interactors.StartInteractor
import ru.kit.rediexpress.domain.interactors.impl.AuthInteractorImpl
import ru.kit.rediexpress.domain.interactors.impl.StartInteractorImpl
import org.koin.dsl.module

val domainModule = module {

    factory<StartInteractor> {
        StartInteractorImpl(
            sharedPrefs = get()
        )
    }

    factory<AuthInteractor> {
        AuthInteractorImpl(
            sharedPrefs = get()
        )
    }
}