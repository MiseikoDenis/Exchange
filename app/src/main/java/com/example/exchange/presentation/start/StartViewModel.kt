package com.example.exchange.presentation.start

import android.app.Application
import androidx.lifecycle.*
import com.example.exchange.models.Currency
import com.example.exchange.presentation.appComponent
import com.example.exchange.repository.CurrenciesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class StartViewModel(application: Application) : ViewModel() {

    @Inject
    lateinit var currenciesList: LiveData<List<Currency>>

    @Inject
    lateinit var currenciesRepository: CurrenciesRepository

    @Inject
    lateinit var listAbbreviation: LiveData<List<String>>

    private var firstRate = 0.0
    private var secondRate = 0.0
    private var thirdRate = 0.0

    private var _bynAmount = MutableLiveData(0.0)
    val bynAmount: LiveData<Double>
        get() = _bynAmount

    private var _firstCurrencyAmount = MutableLiveData(firstRate)
    val firstCurrencyAmount: LiveData<Double>
        get() = _firstCurrencyAmount

    private var _secondCurrencyAmount = MutableLiveData(0.0)
    val secondCurrencyAmount: LiveData<Double>
        get() = _secondCurrencyAmount

    private var _thirdCurrencyAmount = MutableLiveData(0.0)
    val thirdCurrencyAmount: LiveData<Double>
        get() = _thirdCurrencyAmount

    init {
        application.appComponent.inject(this)
    }

    fun updateRate(currency: Currency) {
        viewModelScope.launch {
            firstRate = currency.rate
            _firstCurrencyAmount.value = firstRate
        }
    }

    fun updateCurrencyAmount(){

    }


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(StartViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return StartViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}