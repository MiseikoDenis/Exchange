package com.example.exchange.api

import com.squareup.moshi.Json

data class NetworkDynamic(
    @Json(name = "Cur_ID")
    val id: Int,
    @Json(name = "Date")
    val date: String,
    @Json(name = "Cur_OfficialRate")
    val rate: Double,
)