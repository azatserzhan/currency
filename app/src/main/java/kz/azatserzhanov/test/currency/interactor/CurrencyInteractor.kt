package kz.azatserzhanov.test.currency.interactor

import io.reactivex.Single
import kz.azatserzhanov.test.currency.api.ExchangeApiService
import kz.azatserzhanov.test.currency.model.Currency

class CurrencyInteractor(val apiService: ExchangeApiService) {
    fun getCurrency(): Single<Currency> = apiService.get()
}