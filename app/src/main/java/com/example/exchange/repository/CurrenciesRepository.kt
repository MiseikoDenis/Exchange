package com.example.exchange.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.exchange.api.CurrencyApi
import com.example.exchange.database.CurrencyDatabase
import com.example.exchange.database.DatabaseCurrency
import com.example.exchange.database.asDomainModel
import com.example.exchange.models.Currency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrenciesRepository(private val database: CurrencyDatabase) {

    val currencies: LiveData<List<Currency>> = Transformations.map(database.currencyDao.getCurrencies()) {
        it.asDomainModel()
    }

    suspend fun refreshCurrencies() {
        withContext(Dispatchers.IO) {
            val currenciesList = CurrencyApi.retrofitService.getCurrenciesList()
            val databaseList = currenciesList.map {
                DatabaseCurrency(
                    id = it.id,
                    name = it.name,
                    dateEnd = it.dateEnd
                )
            }
            database.currencyDao.insertCurrency(databaseList)
        }
    }
}