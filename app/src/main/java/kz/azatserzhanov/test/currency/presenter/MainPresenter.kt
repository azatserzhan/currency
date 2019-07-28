package kz.azatserzhanov.test.currency.presenter

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.azatserzhanov.test.common.BasePresenter
import kz.azatserzhanov.test.currency.interactor.CurrencyInteractor
import kz.azatserzhanov.test.currency.contract.MainContract
import kz.azatserzhanov.test.currency.model.Currency

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
                },
                { error -> error.printStackTrace() }
            )
    }

    override fun setCurrencyResult(inputValue: Double) {
        currency?.rates?.entries?.forEachIndexed { index, entry ->
            if(entry.key == resultCurrencyName){
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

    private fun setResultCurrencyValue(){
        currency?.rates?.entries?.forEachIndexed { index, entry ->
            if(entry.key == resultCurrencyName){
                resultCurrencyValue = entry.value
                Log.d("azat", "setCurrencyResult: $resultCurrencyValue")
            }
        }
    }
}