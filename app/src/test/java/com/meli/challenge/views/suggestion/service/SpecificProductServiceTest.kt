package com.meli.challenge.views.suggestion.service

import com.meli.challenge.core.api.ApiClient
import org.junit.Assert
import org.junit.Test

class SpecificProductServiceTest {
    @Test
    fun getFakeServiceInstance() {
        val service = ApiClient.retrofit.create(SuggestionService::class.java)
        Assert.assertNotNull(service)
    }
}