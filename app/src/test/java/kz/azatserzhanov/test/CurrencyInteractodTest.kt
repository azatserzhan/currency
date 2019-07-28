package kz.azatserzhanov.test

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import kz.azatserzhanov.test.currency.api.ExchangeApiService
import kz.azatserzhanov.test.currency.interactor.CurrencyInteractor
import kz.azatserzhanov.test.currency.model.Currency
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CurrencyInteractodTest {
    private var apiService: ExchangeApiService = mockk()
    private lateinit var currencyInteractor: CurrencyInteractor
    @Before
    fun before(){
        currencyInteractor = CurrencyInteractor(apiService)
    }

    @Test
    fun getCurrencyTest(){
        every { currencyInteractor.getCurrency() } returns Single.just(Currency(mapOf(), "base", "date"))
        val subscription = currencyInteractor.getCurrency().test()

        subscription
            .assertNoErrors()
            .assertComplete()
            .dispose()
    }
}
