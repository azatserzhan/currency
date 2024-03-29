package kz.azatserzhanov.test.currency.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.currency_fragment.*
import kz.azatserzhanov.test.R
import kz.azatserzhanov.test.common.BaseFragment
import kz.azatserzhanov.test.currency.contract.MainContract
import kz.azatserzhanov.test.currency.model.CurrencyItem
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
    private var isCurrentEditTextClicked = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.currency_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        presenter.loadCurrency()
    }

    override fun showResultCurrency(total: String) {
        resultEditText.setText(total)
    }

    override fun showCurrentCurrency(total: String) {
        currentEditText.setText(total)
    }

    override fun showResultButton(state: Boolean) {
        showResultButton.isVisible = state
    }

    override fun showCurrencyList(list: List<CurrencyItem>) {
        val currencyAdapter = CurrencyAdapter(
            clickListener = {
                currencyClick(it)
            }
        )

        val currencyManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        currenciesRecyclerView.apply {
            layoutManager = currencyManager
            adapter = currencyAdapter
        }

        currencyAdapter.addItems(list)
    }

    override fun showResultValueText(text: String) {
        resultTextView.text = text
    }

    private fun setupViews() {
        showResultButton.setOnClickListener {
            if (isCurrentEditTextClicked) {
                if (currentEditText.text.isNotEmpty()) {
                    val result = currentEditText.text.toString().toDouble()
                    presenter.chooseCurrency(true, result)
                }
            } else {
                if (resultEditText.text.isNotEmpty()) {
                    val result = resultEditText.text.toString().toDouble()
                    presenter.chooseCurrency(false, result)
                }
            }
        }

        resultEditText.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                isCurrentEditTextClicked = false
            }
            false
        }

        currentEditText.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                isCurrentEditTextClicked = true
            }
            false
        }

        resultTextView.setOnClickListener {
            presenter.setCurrencyList()
        }
    }

    private fun currencyClick(position: Int) {
        Log.d("azat", "position: $position")
        presenter.changResultCurrencyValue(position)
    }
}