package kz.azatserzhanov.test.currency.presenter

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.azatserzhanov.test.common.BasePresenter
import kz.azatserzhanov.test.currency.contract.MainContract
import kz.azatserzhanov.test.currency.interactor.CurrencyInteractor
import kz.azatserzhanov.test.currency.model.Currency
import kz.azatserzhanov.test.currency.model.CurrencyItem

class MainPresenter(private val currencyInteractor: CurrencyInteractor) : BasePresenter<MainContract.View>(),
    MainContract.Presenter {
    private var currency: Currency? = null
    private val resultCurrencyName = "RUB"
    private var resultCurrencyValue = 0.0

    override fun loadCurrency() {
        currencyInteractor.getCurrency()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    currency = result
                    setResultCurrencyValue()
                    view?.showResultButton(true)
                    setCurrencyList()
                },
                { error -> error.printStackTrace() }
            )
    }

    override fun setCurrencyResult(inputValue: Double) {
        currency?.rates?.entries?.forEachIndexed { index, entry ->
            if (entry.key == resultCurrencyName) {
                val result = entry.value * inputValue
                view?.showResultCurrency(result.toString())
                Log.d("azat", "setCurrencyResult: $result")
            }
        }
    }

    override fun currentCurrencyChange(inputValue: Double) {
        val result = inputValue * resultCurrencyValue
        view?.showResultCurrency(result.toString())
    }

    override fun resultCurrencyChange(inputValue: Double) {
        val result = inputValue / resultCurrencyValue
        view?.showCurrentCurrency(result.toString())
    }

    override fun chooseCurrency(isCurrent: Boolean, inputValue: Double) {
        if (isCurrent) {
            currentCurrencyChange(inputValue)
        } else {
            resultCurrencyChange(inputValue)
        }
    }

    private fun setResultCurrencyValue() {
        currency?.rates?.entries?.forEachIndexed { index, entry ->
            if (entry.key == resultCurrencyName) {
                resultCurrencyValue = entry.value
                Log.d("azat", "setCurrencyResult: $resultCurrencyValue")
            }
        }
    }

    override fun setCurrencyList() {
        val list = currency?.rates?.map { CurrencyItem(it.key, it.value) }
        list?.let { view?.showCurrencyList(it) }
    }
}