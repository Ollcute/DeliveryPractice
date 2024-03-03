package ru.kit.rediexpress.app

import android.app.Application
import ru.kit.rediexpress.di.domainModule
import ru.kit.rediexpress.di.repositoryModule
import ru.kit.rediexpress.di.sharedPreferencesModule
import ru.kit.rediexpress.di.uiModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.NONE)
            modules(
                domainModule,
                repositoryModule,
                sharedPreferencesModule,
                uiModule
            )
        }
    }
}