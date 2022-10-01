package com.example.exchange.presentation.currencies

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchange.api.CurrencyApi
import com.example.exchange.api.NetworkCurrency
import kotlinx.coroutines.launch
import java.time.LocalDateTime


class CurrenciesViewModel : ViewModel() {


    private val _currenciesList = MutableLiveData<List<NetworkCurrency>>()
    val currenciesList: LiveData<List<NetworkCurrency>>
        get() = _currenciesList

    init {
        getCurrencyList()
    }

    @SuppressLint("NewApi")
    private fun getCurrencyList() {
        viewModelScope.launch {
            _currenciesList.value = CurrencyApi.retrofitService.getCurrenciesList()
                .toMutableList()
                .filter { LocalDateTime.parse(it.dateEnd) > LocalDateTime.now() }
                .toList()
                .sortedBy { it.name }
        }
    }
}