package com.example.exchange.api

import com.squareup.moshi.Json


data class NetworkCurrency(
    @Json(name = "Cur_ID")
    val id: Int,
    @Json(name = "Cur_Name")
    val name: String,
    @Json(name = "Cur_Abbreviation")
    val abbreviation: String,
    @Json(name = "Cur_DateEnd")
    val dateEnd: String,
    @Json(name = "Cur_QuotName")
    val quoteName: String,
)
