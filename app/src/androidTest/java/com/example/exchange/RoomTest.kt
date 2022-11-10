package com.example.exchange

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.exchange.database.CurrencyDao
import com.example.exchange.database.CurrencyDatabase
import com.example.exchange.database.CurrencyDatabaseEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RoomTest {

    private lateinit var dao: CurrencyDao
    private lateinit var db: CurrencyDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, CurrencyDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = db.currencyDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    @Throws(Exception::class)
    fun insertAndGetCurrenciesList() {
        runBlocking {
            val currency = CurrencyDatabaseEntity(
                id = 1,
                name = "name",
                dateEnd = "dateEnd",
                rate = 2.0,
                abbreviation = "abb",
                scale = 1,
                quoteName = "100 quote",
            )
            val list = listOf(currency)
            dao.insertAllCurrencies(list)
            val currencies = dao.getCurrencies()
            currencies.observeForever {  }

            assertEquals(currencies.value, list)
        }
    }
}