package com.example.exchange.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currencies")
    fun getCurrencies(): LiveData<List<CurrencyDatabaseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrency(currencies: List<CurrencyDatabaseEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCurrency(currency: CurrencyDatabaseEntity)
}