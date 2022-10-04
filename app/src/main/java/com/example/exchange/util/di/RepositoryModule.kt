package com.example.exchange.util.di

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

import com.example.exchange.api.CurrencyApiService
import com.example.exchange.database.CurrencyDao
import com.example.exchange.database.CurrencyDatabase
import com.example.exchange.database.CurrencyDatabaseEntity
import com.example.exchange.database.asDomainModel
import com.example.exchange.models.Currency
import com.example.exchange.repository.CurrenciesRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, RoomModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        database: CurrencyDatabase,
        retrofitService: CurrencyApiService
    ): CurrenciesRepository = CurrenciesRepository(database, retrofitService)

    @Provides
    fun provideCurrenciesList(repository: CurrenciesRepository) = repository.currencies
}