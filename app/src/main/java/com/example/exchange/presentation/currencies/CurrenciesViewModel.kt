package com.example.exchange.presentation.currencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchange.models.Currency
import com.example.exchange.repository.CurrenciesRepository
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


class CurrenciesViewModel @Inject constructor(
    val currenciesRepository: CurrenciesRepository,
    val currenciesList: LiveData<List<Currency>>
) : ViewModel() {

    private var _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshCurrencyList()
    }

    private fun refreshCurrencyList(){
        viewModelScope.launch {
            try {
                currenciesRepository.refreshCurrencies()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {
                if(currenciesList.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}