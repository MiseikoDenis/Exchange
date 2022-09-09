package com.example.exchange.data.api

import com.example.exchange.domain.entity.Currency
import retrofit2.http.GET

interface CurrencyApiService {

    @GET("currencies")
    suspend fun getCurrenciesList(): List<Currency>
}