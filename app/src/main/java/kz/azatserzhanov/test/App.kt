package kz.azatserzhanov.test

import android.app.Application
import kz.azatserzhanov.test.currency.ExchangeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(ExchangeModule.create())
        }
    }
}