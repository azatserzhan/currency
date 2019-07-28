package kz.azatserzhanov.test.currency.presenter

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.azatserzhanov.test.common.BasePresenter
import kz.azatserzhanov.test.currency.interactor.CurrencyInteractor
import kz.azatserzhanov.test.currency.contract.MainContract

class MainPresenter(private val currencyInteractor: CurrencyInteractor) : BasePresenter<MainContract.View>(),
    MainContract.Presenter {

    override fun loadCurrency() {
        currencyInteractor.getCurrency()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Log.d("Currency", "There are ${result.base}")
                }, { error ->
                    error.printStackTrace()
                }
            )
    }
}