package com.example.exchange.presentation.start

import android.app.Application
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exchange.R
import com.example.exchange.models.Currency
import com.example.exchange.presentation.appComponent
import com.example.exchange.repository.CurrenciesRepository
import com.example.exchange.util.Constants.Companion.BASE_RATE
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

    fun updateEditText(editText: EditText?, rate: Double) {
        when (editText?.id) {
            R.id.edit_first -> {
                firstRate = rate
                _firstCurrencyAmount.value = _bynAmount.value?.times(firstRate)
            }
            R.id.edit_second -> {
                secondRate = rate
                _secondCurrencyAmount.value = _bynAmount.value?.times(secondRate)
            }
            R.id.edit_third -> {
                thirdRate = rate
                _thirdCurrencyAmount.value = _bynAmount.value?.times(thirdRate)
            }
            R.id.edit_fourth -> {
                fourthRate = rate
                _fourthCurrencyAmount.value = _bynAmount.value?.times(fourthRate)
            }
        }
    }

    fun updateByn(amount: Double, editText: EditText?){
        when(editText?.id){
            R.id.edit_first -> _bynAmount.value = amount/firstRate
            R.id.edit_second -> _bynAmount.value = amount/secondRate
            R.id.edit_third -> _bynAmount.value = amount/thirdRate
            R.id.edit_fourth -> _bynAmount.value = amount/fourthRate
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