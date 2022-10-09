package com.example.exchange.presentation.dynamic

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.exchange.R
import com.example.exchange.api.NetworkDynamic

class DynamicAdapter : RecyclerView.Adapter<DynamicAdapter.ViewHolder>() {


    private var items: List<NetworkDynamic> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitItem(listItem: List<NetworkDynamic>) {
        items = listItem
        notifyDataSetChanged()
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.item_dynamic, parent, false)
                return ViewHolder(view)
            }
        }

        fun bind(networkDynamic: NetworkDynamic) {
            val date: TextView = itemView.findViewById(R.id.date)
            val rate: TextView = itemView.findViewById(R.id.rate)

            date.text = networkDynamic.date.substring(0, 10).replace("-",".")
            rate.text = networkDynamic.rate.toString()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<NetworkDynamic>() {
        override fun areItemsTheSame(oldItem: NetworkDynamic, newItem: NetworkDynamic): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NetworkDynamic, newItem: NetworkDynamic): Boolean {
            return oldItem.id == newItem.id
        }
    }
}