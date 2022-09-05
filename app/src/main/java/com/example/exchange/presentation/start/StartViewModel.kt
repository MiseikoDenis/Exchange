package com.example.exchange.presentation.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchange.CurrencyApi
import com.example.exchange.domain.entity.Currency
import kotlinx.coroutines.launch

class StartViewModel : ViewModel() {


    private val _currenciesList = MutableLiveData<List<Currency>>()
    val currenciesList: LiveData<List<Currency>>
        get() = _currenciesList

    init {
        getText()
    }

    //Тестовый метод
    private fun getText() {
        viewModelScope.launch() {
            _currenciesList.value = CurrencyApi.retrofitService.getProperties()
        }
    }

}