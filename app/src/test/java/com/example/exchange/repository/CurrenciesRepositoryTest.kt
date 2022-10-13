package com.example.exchange.repository

import com.example.exchange.api.CurrencyApiService
import com.example.exchange.api.NetworkCurrency
import com.example.exchange.database.CurrencyDao
import com.example.exchange.database.CurrencyDatabase
import com.example.exchange.database.CurrencyDatabaseEntity
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import java.time.LocalDateTime

@OptIn(ExperimentalCoroutinesApi::class)
class CurrenciesRepositoryTest {

    @get:Rule
    val rule = MockKRule(this)

    @MockK
    lateinit var api: CurrencyApiService

    @MockK
    lateinit var db: CurrencyDatabase

    @MockK
    lateinit var currencyDao: CurrencyDao

//    private fun createRepository() = CurrenciesRepository(db, api)

//    @Test
//    fun refreshCurrencies_andAddThemToDatabase() = runTest {
//        val repo = createRepository()
//        coEvery { db.currencyDao } answers { currencyDao }
//        repo.refreshCurrencies()
//        coVerify(exactly = 1) { api.getCurrenciesList() }
//        coVerify(exactly = 1) { api.getExchangeRate(any()) }
//    }


}