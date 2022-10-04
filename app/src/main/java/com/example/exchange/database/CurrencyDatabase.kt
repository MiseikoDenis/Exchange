package com.example.exchange.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyDatabaseEntity::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract val currencyDao: CurrencyDao
}