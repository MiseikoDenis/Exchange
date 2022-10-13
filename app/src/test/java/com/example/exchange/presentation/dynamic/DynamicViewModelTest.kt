package com.example.exchange.presentation.dynamic

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import org.junit.Before
import org.junit.Test


class DynamicViewModelTest {

    @InjectMockKs
    lateinit var viewModel: DynamicViewModel

    @Before
    fun setup(){
        MockKAnnotations.init(this)
    }

    @Test
    fun refreshIdTest(){
        val id = 1
        viewModel.refreshId(id)

        assert(viewModel.id == id)
    }
}