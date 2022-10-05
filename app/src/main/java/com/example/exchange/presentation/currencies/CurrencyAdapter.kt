package com.example.exchange.presentation.currencies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exchange.R
import com.example.exchange.models.Currency


class CurrencyAdapter : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {


    private var items: List<Currency> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitItem(listItem: List<Currency>) {
        items = listItem
        notifyDataSetChanged()
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.item_currency, parent, false)
                return ViewHolder(view)
            }
        }

        fun bind(currency: Currency) {
            val name: TextView = itemView.findViewById(R.id.currency_name)
            val rate: TextView = itemView.findViewById(R.id.currency_rate)

            name.text = currency.name
            rate.text = currency.rate.toString()
        }
    }
}

