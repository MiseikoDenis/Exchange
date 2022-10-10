package com.example.exchange.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.exchange.api.CurrencyApiService
import com.example.exchange.database.CurrencyDatabase
import com.example.exchange.database.CurrencyDatabaseEntity
import com.example.exchange.database.asDomainModel
import com.example.exchange.models.Currency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject


class CurrenciesRepository @Inject constructor(
    val database: CurrencyDatabase,
    private val retrofitService: CurrencyApiService
) {

    val currencies: LiveData<List<Currency>> =
        Transformations.map(database.currencyDao.getCurrencies()) {
            it.asDomainModel().sortedBy { currency -> currency.name }
        }

    val abreviatures: LiveData<List<String>> =
        Transformations.map(currencies) {
            it.map { it.abbreviation }.sortedBy { it }
        }

    suspend fun getDynamic(id: Int, start: String, end: String) =
        retrofitService.getCurrencyDynamic(id, start, end)

    @SuppressLint("NewApi")
    suspend fun refreshCurrencies() {
        withContext(Dispatchers.IO) {
            val currenciesList = retrofitService.getCurrenciesList()
                .filter { LocalDateTime.parse(it.dateEnd) > LocalDateTime.now() }
            val databaseList = currenciesList.map {
                CurrencyDatabaseEntity(
                    id = it.id,
                    name = it.name,
                    abbreviation = it.abbreviation,
                    dateEnd = it.dateEnd,
                    quoteName = it.quoteName,
                    rate = retrofitService.getExchangeRate(it.id).rate,
                )
            }
            database.currencyDao.insertAllCurrencies(databaseList)
        }
    }
}