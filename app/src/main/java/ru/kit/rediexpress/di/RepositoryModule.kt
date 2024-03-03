package ru.kit.rediexpress.di

import ru.kit.rediexpress.data.repository.SharedPrefsRepository
import ru.kit.rediexpress.data.repository.impl.SharedPrefsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<SharedPrefsRepository> {
        SharedPrefsRepositoryImpl(
            handler = get()
        )
    }
}