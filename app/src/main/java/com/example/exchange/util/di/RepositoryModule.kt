package com.example.exchange.util.di

import com.example.exchange.repository.CurrenciesRepositoryImpl
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class, RoomModule::class, BindersModule::class])
class RepositoryModule {

    @Provides
    fun provideCurrenciesList(repository: CurrenciesRepositoryImpl) = repository.currencies

    @Provides
    fun provideAbbreviations(repository: CurrenciesRepositoryImpl) = repository.abbreviations
}