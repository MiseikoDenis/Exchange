package com.example.exchange.repository

import com.example.exchange.api.NetworkDynamic

interface CurrenciesRepository{

    suspend fun getDynamic(id: Int, start: String, end: String): List<NetworkDynamic>

    suspend fun refreshCurrencies()
}