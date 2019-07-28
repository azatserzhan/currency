package kz.azatserzhanov.test.currency.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_currency.view.*
import kz.azatserzhanov.test.R
import kz.azatserzhanov.test.currency.model.CurrencyItem

class CurrencyAdapter(
    private val clickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val currencies = mutableListOf<CurrencyItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BotViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = currencies.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BotViewHolder).bind(currencies[position], clickListener)
    }

    fun addItems(list: List<CurrencyItem>) {
        currencies.addAll(list)
        // notifyItemRangeInserted(currencies.size + 1, currencies.size)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): CurrencyItem = currencies[position]

    private class BotViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_currency, parent, false)) {
        private val currencyTextView = itemView.currencyTextView

        fun bind(currency: CurrencyItem, clickListener: (position: Int) -> Unit) {
            currencyTextView.text = currency.name
            currencyTextView.setOnClickListener {
                clickListener(adapterPosition)
            }
        }
    }
}