package ru.kit.rediexpress.di

import ru.kit.rediexpress.data.sharedPrefs.SharedPrefsHandler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sharedPreferencesModule = module {
    single { SharedPrefsHandler(androidContext().applicationContext) }
}