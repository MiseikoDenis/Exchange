package com.example.exchange.presentation.currencies

import android.app.Application
import androidx.lifecycle.*
import com.example.exchange.database.getDatabase
import com.example.exchange.models.Currency
import com.example.exchange.presentation.appComponent
import com.example.exchange.repository.CurrenciesRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.LocalDateTime
import javax.inject.Inject


class CurrenciesViewModel(application: Application) : ViewModel() {

    @Inject
    lateinit var currenciesRepository: CurrenciesRepository

    @Inject
    lateinit var currenciesList: LiveData<List<Currency>>

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        application.appComponent.inject(this)
        refreshCurrencyList()
    }

//    @SuppressLint("NewApi")
//    private fun refreshCurrencyList() {
//        viewModelScope.launch {
//            _currenciesList.value = CurrencyApi.retrofitService.getCurrenciesList()
//                .toMutableList()
//                .filter { LocalDateTime.parse(it.dateEnd) > LocalDateTime.now() }
//                .toList()
//                .sortedBy { it.name }
//        }
//    }

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

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CurrenciesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CurrenciesViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}