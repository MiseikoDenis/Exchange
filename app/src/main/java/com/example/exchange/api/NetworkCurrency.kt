package com.example.exchange.api

import com.example.exchange.database.DatabaseCurrency
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class NetworkCurrency(
    @Json(name = "Cur_ID")
    val id: Int,
    @Json(name = "Cur_Name")
    val name: String,
    @Json(name = "Cur_DateEnd")
    val dateEnd: String,
)
