package kz.azatserzhanov.test.currency

import kz.azatserzhanov.test.common.InjectionModule
import kz.azatserzhanov.test.currency.api.ExchangeApiService
import kz.azatserzhanov.test.currency.interactor.CurrencyInteractor
import kz.azatserzhanov.test.currency.presenter.MainPresenter
import org.koin.dsl.module

object ExchangeModule : InjectionModule {
    override fun create() = module {
        single { MainPresenter(get()) }
        single { CurrencyInteractor(get()) }
        single { ExchangeApiService.create() }
    }
}