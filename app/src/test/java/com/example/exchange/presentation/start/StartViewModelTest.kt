package com.example.exchange.presentation.start

import com.example.exchange.util.Constants.Companion.FIRST_FIELD
import io.mockk.impl.annotations.InjectMockKs
import org.junit.After
import org.junit.Before

class StartViewModelTest {

    @InjectMockKs
    lateinit var viewModel: StartViewModel

    @Before
    fun before() {
        val id = FIRST_FIELD

    }

    @After
    fun tearDown() {

    }

}