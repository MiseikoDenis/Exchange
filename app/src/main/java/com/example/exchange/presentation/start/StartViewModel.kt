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
    private var fourthRate = 0.0

    private var _bynAmount = MutableLiveData(1.0)
    val bynAmount: LiveData<Double>
        get() = _bynAmount

    private var _firstCurrencyAmount = MutableLiveData(_bynAmount.value?.times(firstRate))
    val firstCurrencyAmount: LiveData<Double?>
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

    fun updateRate(editText: EditText?, rate: Double) {
        when (editText?.id) {
           R.id.edit_first  -> firstRate = rate
           R.id.edit_second  -> secondRate = rate
           R.id.edit_third  -> thirdRate = rate
           R.id.edit_fourth  -> fourthRate = rate
            else -> return
        }
    }

    fun updateByn(amount: Double, currency: Currency) {
        _bynAmount.postValue(amount.div(currency.rate))
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