package com.example.exchange.models

data class Currency(
    val id: Int,
    val name: String,
    val abbreviation: String,
    val dateEnd: String,
    val rate: Double,
)