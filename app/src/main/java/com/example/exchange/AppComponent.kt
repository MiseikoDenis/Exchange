package com.example.exchange

import com.example.exchange.repository.CurrenciesRepository
import com.example.exchange.util.di.NetworkModule
import com.example.exchange.util.di.RoomModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

fun inject(currenciesRepository: CurrenciesRepository)
}