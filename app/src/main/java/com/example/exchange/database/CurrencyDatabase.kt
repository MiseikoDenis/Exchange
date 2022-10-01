package com.example.exchange.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseCurrency::class], version = 1)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract val currencyDao: CurrencyDao
}

private lateinit var INSTANCE: CurrencyDatabase

fun getDatabase(context: Context): CurrencyDatabase {
    synchronized(CurrencyDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                CurrencyDatabase::class.java,
                "currencies"
            )
                .build()
        }
    }
    return INSTANCE
}