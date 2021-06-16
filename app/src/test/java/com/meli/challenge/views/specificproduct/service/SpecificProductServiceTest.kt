package com.meli.challenge.views.specificproduct.service

import com.meli.challenge.core.api.ApiClient
import org.junit.Assert
import org.junit.Test

class SpecificProductServiceTest {
    @Test
    fun getFakeServiceInstance() {
        val service = ApiClient.retrofit.create(SpecificProductService::class.java)
        Assert.assertNotNull(service)
    }
}