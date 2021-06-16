package com.meli.challenge.views.specificproduct.viewmodel

import com.meli.challenge.views.specificproduct.repository.SpecificProductRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SpecificProductViewModelFactoryTest {

    @Mock
    lateinit var repository: SpecificProductRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun viewModelShouldBeNotNull() {
        val factory = SpecificProductViewModelFactory(repository)
        val viewModel = factory.create(SpecificProductViewModel::class.java)
        Assert.assertNotNull(viewModel)
    }
}