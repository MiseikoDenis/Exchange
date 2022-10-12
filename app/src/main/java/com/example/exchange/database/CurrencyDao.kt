package com.example.exchange.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currencies")
    fun getCurrencies(): LiveData<List<CurrencyDatabaseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCurrencies(currencies: List<CurrencyDatabaseEntity>)
}