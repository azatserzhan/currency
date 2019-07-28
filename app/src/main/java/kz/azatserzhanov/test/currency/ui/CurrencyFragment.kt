package kz.azatserzhanov.test.currency.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kz.azatserzhanov.test.R
import kz.azatserzhanov.test.common.BaseFragment
import kz.azatserzhanov.test.currency.contract.MainContract
import kz.azatserzhanov.test.currency.presenter.MainPresenter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencyFragment : BaseFragment<MainContract.View, MainContract.Presenter>(),
    MainContract.View {

    companion object {
        fun create() = CurrencyFragment()
    }

    private val presenterImpl: MainPresenter by viewModel()
    override val presenter: MainContract.Presenter
        get() = presenterImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.currency_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.loadCurrency()
    }

    override fun showCurrency() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}