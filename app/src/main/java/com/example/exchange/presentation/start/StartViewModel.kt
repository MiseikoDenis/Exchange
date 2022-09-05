package com.example.exchange.presentation.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchange.CurrencyApi
import com.example.exchange.domain.entity.Currency
import kotlinx.coroutines.launch

class StartViewModel : ViewModel() {

    private var currenciesList: MutableList<Currency> = mutableListOf()

    private lateinit var text: String

    init {
        viewModelScope.launch {
            currenciesList = CurrencyApi.retrofitService.getProperties().toMutableList()
        }
    }

    fun getText(): String {
        text = currenciesList.toString()
        return text
    }

}