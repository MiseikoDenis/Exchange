package com.example.exchange.presentation.currencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchange.data.api.CurrencyApi
import com.example.exchange.domain.entity.Currency
import kotlinx.coroutines.launch

class CurrenciesViewModel : ViewModel() {


    private val _currenciesList = MutableLiveData<List<Currency>>()
    val currenciesList: LiveData<List<Currency>>
        get() = _currenciesList

    init {
        getCurrencyList()
    }

    private fun getCurrencyList() {
        viewModelScope.launch {
            _currenciesList.value = CurrencyApi.retrofitService.getCurrenciesList()
        }
    }
}