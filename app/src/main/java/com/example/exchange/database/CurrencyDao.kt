package com.example.exchange.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currencies")
    fun getCurrencies(): LiveData<List<DatabaseCurrency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencies(currencies: List<DatabaseCurrency>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCurrency(currency: DatabaseCurrency)
}