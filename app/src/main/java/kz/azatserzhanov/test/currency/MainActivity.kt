package kz.azatserzhanov.test.currency

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kz.azatserzhanov.test.R
import kz.azatserzhanov.test.common.BaseActivity
import kz.azatserzhanov.test.currency.contract.MainContract
import kz.azatserzhanov.test.currency.presenter.MainPresenter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(),
    MainContract.View,
    MainContract {
    private val presenter: MainPresenter by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.loadCurrency()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showCurrency() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
