package com.example.exchange.presentation.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exchange.models.Currency
import com.example.exchange.repository.CurrenciesRepository
import com.example.exchange.util.Constants.Companion.BASE_RATE
import com.example.exchange.util.Constants.Companion.BASE_SCALE
import com.example.exchange.util.Constants.Companion.BYN_FIELD
import com.example.exchange.util.Constants.Companion.FIRST_FIELD
import com.example.exchange.util.Constants.Companion.FOURTH_FIELD
import com.example.exchange.util.Constants.Companion.SECOND_FIELD
import com.example.exchange.util.Constants.Companion.THIRD_FIELD
import javax.inject.Inject


class StartViewModel @Inject constructor(
    val currenciesList: LiveData<List<Currency>>,
    val currenciesRepository: CurrenciesRepository,
    val listAbbreviation: LiveData<List<String>>
) : ViewModel() {

    private var firstRate = BASE_RATE
    private var secondRate = BASE_RATE
    private var thirdRate = BASE_RATE
    private var fourthRate = BASE_RATE

    private var firstScale = BASE_SCALE
    private var secondScale = BASE_SCALE
    private var thirdScale = BASE_SCALE
    private var fourthScale = BASE_SCALE

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



    //Обновить курс валюты в определенном поле
    fun updateRate(id: Int, rate: Double, scale: Int) {
        when (id) {
            FIRST_FIELD -> {
                firstRate = rate
                firstScale = scale
            }
            SECOND_FIELD -> {
                secondRate = rate
                secondScale = scale
            }
            THIRD_FIELD -> {
                thirdRate = rate
                thirdScale = scale
            }
            FOURTH_FIELD -> {
                fourthRate = rate
                fourthScale = scale
            }
        }
        updateAmount(id)
    }

    //Обновить значение валюты в поле
    private fun updateAmount(id: Int) {
        when (id) {
            FIRST_FIELD -> _firstCurrencyAmount.value = _bynAmount.value?.times((firstScale/firstRate))
            SECOND_FIELD -> _secondCurrencyAmount.value = _bynAmount.value?.times((secondScale/secondRate))
            THIRD_FIELD -> _thirdCurrencyAmount.value = _bynAmount.value?.times((thirdScale/thirdRate))
            FOURTH_FIELD -> _fourthCurrencyAmount.value = _bynAmount.value?.times((fourthScale/fourthRate))
        }
    }

    //Обновить значения всех полей кроме выбранного
    fun updateOtherFields(amount: Double, id: Int) {
        when (id) {
            FIRST_FIELD -> {
                _bynAmount.value = amount / (firstScale/firstRate)
                updateAmount(SECOND_FIELD)
                updateAmount(THIRD_FIELD)
                updateAmount(FOURTH_FIELD)
            }
            SECOND_FIELD -> {
                _bynAmount.value = amount / (secondScale/secondRate)
                updateAmount(FIRST_FIELD)
                updateAmount(THIRD_FIELD)
                updateAmount(FOURTH_FIELD)
            }
            THIRD_FIELD -> {
                _bynAmount.value = amount / (thirdScale/thirdRate)
                updateAmount(FIRST_FIELD)
                updateAmount(SECOND_FIELD)
                updateAmount(FOURTH_FIELD)
            }
            FOURTH_FIELD -> {
                _bynAmount.value = amount / (fourthScale/fourthRate)
                updateAmount(FIRST_FIELD)
                updateAmount(SECOND_FIELD)
                updateAmount(THIRD_FIELD)
            }
            BYN_FIELD -> {
                _firstCurrencyAmount.value = amount.times((firstScale/firstRate))
                _secondCurrencyAmount.value = amount.times((secondScale/secondRate))
                _thirdCurrencyAmount.value = amount.times((thirdScale/thirdRate))
                _fourthCurrencyAmount.value = amount.times((fourthScale/fourthRate))
            }
        }
    }
}