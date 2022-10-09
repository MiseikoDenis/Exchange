package com.example.exchange.presentation.dynamic

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.*
import com.example.exchange.api.CurrencyApiService
import com.example.exchange.api.NetworkDynamic
import com.example.exchange.models.Currency
import com.example.exchange.presentation.appComponent
import com.example.exchange.repository.CurrenciesRepository
import com.example.exchange.util.Constants.Companion.BASE_DATE
import com.example.exchange.util.Constants.Companion.BASE_ID
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.LocalDate
import javax.inject.Inject

@SuppressLint("NewApi")
class DynamicViewModel(application: Application) : ViewModel() {

    @Inject
    lateinit var currenciesList: LiveData<List<Currency>>

    @Inject
    lateinit var retrofit: CurrencyApiService

    @Inject
    lateinit var currencyRepository: CurrenciesRepository

    private var _dynamicList = MutableLiveData<List<NetworkDynamic>>()
    val dynamicList: LiveData<List<NetworkDynamic>>
        get() = _dynamicList

    private var _id = BASE_ID
    val id: Int
        get() = _id

    private var _startDate = BASE_DATE

    private var _endDate = BASE_DATE

    init {
        application.appComponent.inject(this)
        _endDate = LocalDate.now().toString()
        _startDate = LocalDate.now().toString()
    }

    fun refreshDynamicList() {
        viewModelScope.launch {
            try {
                _dynamicList.value = currencyRepository.getDynamic(_id, _startDate, _endDate).reversed()
            } catch (_: IOException) {}
        }
    }

    fun refreshDates(start: String){
        _startDate = start
    }

    fun refreshId(id: Int) {
        _id = id
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DynamicViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DynamicViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}