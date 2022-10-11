package com.example.exchange.presentation.start

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exchange.models.Currency
import com.example.exchange.presentation.appComponent
import com.example.exchange.repository.CurrenciesRepository
import com.example.exchange.util.Constants.Companion.BASE_RATE
import com.example.exchange.util.Constants.Companion.BYN_FIELD
import com.example.exchange.util.Constants.Companion.FIRST_FIELD
import com.example.exchange.util.Constants.Companion.FOURTH_FIELD
import com.example.exchange.util.Constants.Companion.SECOND_FIELD
import com.example.exchange.util.Constants.Companion.THIRD_FIELD
import javax.inject.Inject


class StartViewModel(application: Application) : ViewModel() {

    @Inject
    lateinit var currenciesList: LiveData<List<Currency>>

    @Inject
    lateinit var currenciesRepository: CurrenciesRepository

    @Inject
    lateinit var listAbbreviation: LiveData<List<String>>

    private var firstRate = BASE_RATE
    private var secondRate = BASE_RATE
    private var thirdRate = BASE_RATE
    private var fourthRate = BASE_RATE

    private var _bynAmount = MutableLiveData(1.0)
    val bynAmount: LiveData<Double>
        get() = _bynAmount

    private var _firstCurrencyAmount = MutableLiveData(firstRate)
    val firstCurrencyAmount: LiveData<Double>
        get() = _firstCurrencyAmount

    private var _secondCurrencyAmount = MutableLiveData(secondRate)
    val secondCurrencyAmount: LiveData<Double>
        get() = _secondCurrencyAmount

    private var _thirdCurrencyAmount = MutableLiveData(thirdRate)
    val thirdCurrencyAmount: LiveData<Double>
        get() = _thirdCurrencyAmount

    private var _fourthCurrencyAmount = MutableLiveData(fourthRate)
    val fourthCurrencyAmount: LiveData<Double>
        get() = _fourthCurrencyAmount

    init {
        application.appComponent.inject(this)
    }

    fun updateRate(id: Int, rate: Double) {
        when (id) {
            FIRST_FIELD -> firstRate = rate
            SECOND_FIELD -> secondRate = rate
            THIRD_FIELD -> thirdRate = rate
            FOURTH_FIELD -> fourthRate = rate
        }
        updateAmount(id)
    }

    private fun updateAmount(id: Int) {
        when (id) {
            FIRST_FIELD -> _firstCurrencyAmount.value = _bynAmount.value?.times(firstRate)
            SECOND_FIELD -> _secondCurrencyAmount.value = _bynAmount.value?.times(secondRate)
            THIRD_FIELD -> _thirdCurrencyAmount.value = _bynAmount.value?.times(thirdRate)
            FOURTH_FIELD -> _fourthCurrencyAmount.value = _bynAmount.value?.times(fourthRate)
        }
    }

    fun updateOtherFields(amount: Double, id: Int) {
        when (id) {
            FIRST_FIELD -> {
                _bynAmount.value = amount / firstRate
                updateAmount(SECOND_FIELD)
                updateAmount(THIRD_FIELD)
                updateAmount(FOURTH_FIELD)
            }
            SECOND_FIELD -> {
                _bynAmount.value = amount / secondRate
                updateAmount(FIRST_FIELD)
                updateAmount(THIRD_FIELD)
                updateAmount(FOURTH_FIELD)
            }
            THIRD_FIELD -> {
                _bynAmount.value = amount / thirdRate
                updateAmount(FIRST_FIELD)
                updateAmount(SECOND_FIELD)
                updateAmount(FOURTH_FIELD)
            }
            FOURTH_FIELD -> {
                _bynAmount.value = amount / fourthRate
                updateAmount(FIRST_FIELD)
                updateAmount(SECOND_FIELD)
                updateAmount(THIRD_FIELD)
            }
            BYN_FIELD -> {
                _firstCurrencyAmount.value = amount.times(firstRate)
                _secondCurrencyAmount.value = amount.times(secondRate)
                _thirdCurrencyAmount.value = amount.times(thirdRate)
                _fourthCurrencyAmount.value = amount.times(fourthRate)
            }
        }
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