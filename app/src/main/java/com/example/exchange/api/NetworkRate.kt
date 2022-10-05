package com.example.exchange.api

import com.squareup.moshi.Json

data class NetworkRate(
    @Json(name = "Cur_ID")
    val id: Int,
    @Json(name = "Date")
    val date: String,
    @Json(name = "Cur_Abbreviation")
    val abbreviation: String,
    @Json(name = "Cur_Scale")
    val scale: Int,
    @Json(name = "Cur_Name")
    val name: String,
    @Json(name = "Cur_OfficialRate")
    val rate: Double,
)