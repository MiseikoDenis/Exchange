package com.example.exchange.domain.repository

import com.example.exchange.domain.entity.Currency

interface CurrencyRepository {

    suspend fun getAllCurrencies(): List<Currency>
}