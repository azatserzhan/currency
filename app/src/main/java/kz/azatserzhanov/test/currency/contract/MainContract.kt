package kz.azatserzhanov.test.currency.contract

import kz.azatserzhanov.test.common.MvpPresenter
import kz.azatserzhanov.test.common.MvpView
import kz.azatserzhanov.test.currency.model.CurrencyItem

interface MainContract {

    interface View : MvpView {
        fun showResultCurrency(total: String)
        fun showCurrentCurrency(total: String)
        fun showResultButton(isVisible: Boolean)
        fun showCurrencyList(list: List<CurrencyItem>)
        fun showResultValueText(text: String)
    }

    interface Presenter : MvpPresenter<View> {
        fun loadCurrency()
        fun setCurrencyResult(inputValue: Double)
        fun currentCurrencyChange(inputValue: Double)
        fun resultCurrencyChange(inputValue: Double)
        fun chooseCurrency(isCurrent: Boolean, inputValue: Double)
        fun setCurrencyList()
        fun changResultCurrency(position: Int)
    }
}