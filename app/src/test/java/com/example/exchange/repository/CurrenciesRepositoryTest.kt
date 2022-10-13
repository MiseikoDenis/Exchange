package com.example.exchange.repository

import com.example.exchange.api.CurrencyApiService
import com.example.exchange.database.CurrencyDao
import com.example.exchange.database.CurrencyDatabase
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

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