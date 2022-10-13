package com.example.exchange.database


import com.example.exchange.models.Currency
import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyDatabaseEntityTest {

    @Test
    fun listCurrencyDatabaseEntity_mapsTo_Currency() {
        val databaseEntity = CurrencyDatabaseEntity(
            id = 1,
            name = "name",
            abbreviation = "abb",
            dateEnd = "date",
            rate = 1.1,
            quoteName = "quote",
        )
        val listDatabaseEntity = listOf(databaseEntity)

        val currency = Currency(
            id = 1,
            name = "name",
            abbreviation = "abb",
            dateEnd = "date",
            rate = 1.1,
            quoteName = "quote",
        )
        val listCurrency = listOf(currency)

        assertEquals(listDatabaseEntity.asDomainModel(), listCurrency)
    }
}