package com.example.exchange.util.di

import android.content.Context
import androidx.room.Room
import com.example.exchange.database.CurrencyDao
import com.example.exchange.database.CurrencyDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): CurrencyDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CurrencyDatabase::class.java,
            "currencies"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCurrencyDao(database: CurrencyDatabase): CurrencyDao {
        return database.currencyDao
    }

}
