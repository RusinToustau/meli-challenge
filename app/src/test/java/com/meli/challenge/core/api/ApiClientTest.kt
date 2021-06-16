package com.meli.challenge.core.api

import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ApiClientTest {

    @Test
    fun getFakeServiceInstance() {
        val service = ApiClient.retrofit.create(FakeService::class.java)
        assertNotNull(service)
    }

}

interface FakeService