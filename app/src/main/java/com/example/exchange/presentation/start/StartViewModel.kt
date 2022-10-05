package com.example.exchange.presentation.start

import android.app.Application
import android.widget.EditText
import androidx.lifecycle.*
import com.example.exchange.models.Currency
import com.example.exchange.presentation.appComponent
import com.example.exchange.presentation.currencies.CurrenciesViewModel
import com.example.exchange.repository.CurrenciesRepository
import javax.inject.Inject


class StartViewModel(application: Application) : ViewModel() {

    @Inject
    lateinit var currenciesList: LiveData<List<Currency>>

    @Inject
    lateinit var currencyRepository: CurrenciesRepository

    @Inject
    lateinit var listAbbreviation: LiveData<List<String>>

    private var _bynAmmount = MutableLiveData(0)
    val bynAmmount: LiveData<Int>
        get() = _bynAmmount

    private var _firstCurrencyAmmount = MutableLiveData(0)
    val firstCurrencyAmmount: LiveData<Int>
        get() = _firstCurrencyAmmount

    private var _secondCurrencyAmmount = MutableLiveData(0)
    val secondCurrencyAmmount: LiveData<Int>
        get() = _secondCurrencyAmmount

    private var _thirdCurrencyAmmount = MutableLiveData(0)
    val thirdCurrencyAmmount: LiveData<Int>
        get() = _thirdCurrencyAmmount

    init {
        application.appComponent.inject(this)
    }

    fun updateRate(editText: EditText, abbreviature: String) {

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