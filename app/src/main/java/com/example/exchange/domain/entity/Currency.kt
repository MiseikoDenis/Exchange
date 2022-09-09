package com.example.exchange.domain.entity

import com.squareup.moshi.Json


data class Currency(
    @Json(name = "Cur_ID")
    val id: Int,
    @Json(name = "Cur_Name")
    val name: String,
    @Json(name = "Cur_DateEnd")
    val dateEnd: String,
)