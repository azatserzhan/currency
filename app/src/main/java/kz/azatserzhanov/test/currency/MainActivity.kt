package kz.azatserzhanov.test.currency

import android.os.Bundle
import kz.azatserzhanov.test.R
import kz.azatserzhanov.test.common.BaseActivity
import kz.azatserzhanov.test.currency.ui.CurrencyFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(CurrencyFragment.create())
    }
}
