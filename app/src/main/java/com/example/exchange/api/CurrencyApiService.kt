package com.example.exchange.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyApiService {

    @GET("currencies")
    suspend fun getCurrenciesList(): List<NetworkCurrency>

    @GET("rates/{id}")
    suspend fun getExchangeRate(@Path("id") id: Int): NetworkRate

    @GET("rates/dynamics/{id}?")
    suspend fun getCurrencyDynamic(
        @Path("id") id: Int,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): List<NetworkDynamic>
}