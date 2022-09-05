package com.example.exchange.data.api

import com.example.exchange.domain.entity.Currency

interface CurrencyApi {

    suspend fun getCurrencies() : List<Currency>
}