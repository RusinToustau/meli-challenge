package com.meli.challenge.views.suggestion.viewmodel

import com.meli.challenge.views.suggestion.repository.SuggestionRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ExplorerViewModelFactoryTest {
    @Mock
    lateinit var repository: SuggestionRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun viewModelShouldBeNotNull() {
        val factory = ExplorerViewModelFactory(repository)
        val viewModel = factory.create(ExplorerViewModel::class.java)
        Assert.assertNotNull(viewModel)
    }
}