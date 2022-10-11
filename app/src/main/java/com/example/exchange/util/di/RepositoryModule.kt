package com.example.exchange.util.di

import com.example.exchange.repository.CurrenciesRepository
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class, RoomModule::class])
class RepositoryModule {

    @Provides
    fun provideCurrenciesList(repository: CurrenciesRepository) = repository.currencies

    @Provides
    fun provideAbbreviations(repository: CurrenciesRepository) = repository.abbreviations
}