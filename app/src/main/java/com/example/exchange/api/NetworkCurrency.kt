package com.example.exchange.api

import com.example.exchange.database.DatabaseCurrency
import com.squareup.moshi.Json


data class NetworkCurrency(
    @Json(name = "Cur_ID")
    val id: Int,
    @Json(name = "Cur_Name")
    val name: String,
    @Json(name = "Cur_DateEnd")
    val dateEnd: String,
)

data class NetworkCurrencyContainer(val currencies: List<NetworkCurrency>)

fun NetworkCurrencyContainer.asDatabaseModel(): List<DatabaseCurrency> {
    return currencies.map {
        DatabaseCurrency(
            id = it.id,
            name = it.name,
            dateEnd = it.dateEnd
        )
    }
}