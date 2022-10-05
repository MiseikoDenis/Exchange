package com.example.exchange.api

import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApiService {

    @GET("currencies")
    suspend fun getCurrenciesList(): List<NetworkCurrency>

    @GET("rates/{id}")
    suspend fun getExchangeRate(@Path("id") id: Int): NetworkRate

    @GET("rates/dynamics/{id}?startdate={startDate}&enddate={endDate}")
    suspend fun getCurrencyDynamic(
        @Path("id") id: Int,
        @Path("startDate") startDate: String,
        @Path("endDate") endDate: String
    ): List<NetworkDynamic>
}