package com.example.exchange

import com.example.exchange.presentation.currencies.CurrenciesViewModel
import com.example.exchange.repository.CurrenciesRepository
import com.example.exchange.util.di.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface AppComponent {

    fun inject(target: CurrenciesRepository)
    fun inject(target: CurrenciesViewModel)
}