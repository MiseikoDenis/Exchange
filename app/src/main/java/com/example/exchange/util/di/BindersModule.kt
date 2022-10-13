package com.example.exchange.util.di

import com.example.exchange.repository.CurrenciesRepository
import com.example.exchange.repository.CurrenciesRepositoryImpl
import com.example.exchange.util.preference.SharedPreferencesManager
import com.example.exchange.util.preference.SharedPreferencesManagerImpl
import dagger.Binds
import dagger.Module

@Module
interface BindersModule {

    @Binds
    fun bindsSharedPreferencesManager(sharedPreferencesManagerImpl: SharedPreferencesManagerImpl): SharedPreferencesManager

    @Binds
    fun bindCurrenciesRepository(currenciesRepositoryImpl: CurrenciesRepositoryImpl): CurrenciesRepository
}