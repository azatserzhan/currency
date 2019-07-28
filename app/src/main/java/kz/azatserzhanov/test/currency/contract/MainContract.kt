package kz.azatserzhanov.test.currency.contract

import kz.azatserzhanov.test.common.MvpPresenter
import kz.azatserzhanov.test.common.MvpView

interface MainContract {

    interface View : MvpView {
        fun showResultCurrency(total: String)
    }

    interface Presenter : MvpPresenter<View> {
        fun loadCurrency()
        fun setCurrencyResult(inputValue: Double)
        fun currentCurrencyChange(inputValue: Double)
    }
}