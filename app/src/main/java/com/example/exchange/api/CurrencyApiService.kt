package com.example.exchange.api

import retrofit2.http.GET

interface CurrencyApiService {

    @GET("currencies")
    suspend fun getCurrenciesList(): List<NetworkCurrency>
}