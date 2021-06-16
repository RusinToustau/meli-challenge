package com.meli.challenge.views.specificproduct.repository

import com.meli.challenge.core.basemodel.Status
import com.meli.challenge.model.Item
import com.meli.challenge.views.specificproduct.service.SpecificProductService
import com.utils.ErrorResponses.badRequestResponse
import com.utils.ErrorResponses.unauthorizedResponse
import com.utils.TestUtils.getObjectFromJsonResource
import org.junit.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response
import org.mockito.Mockito.`when`as whenever

@ExperimentalCoroutinesApi
class SpecificProductRepositoryTest {

    @Mock
    private lateinit var service: SpecificProductService

    private lateinit var repository: SpecificProductRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = SpecificProductRepositoryImpl(service)
    }

    @Test
    fun serviceReturnSuccessResponse() = runBlocking {
        whenever(service.getSpecificProduct(FAKE_PRODUCT_ID))
            .thenReturn(SUCCESS_RESPONSE)

        val result = repository.getSpecificProduct(FAKE_PRODUCT_ID)

        assertEquals(result.status, Status.SUCCESS)
        assertEquals(result.data, SUCCESS_RESPONSE.body())
    }

    @Test
    fun serviceReturnAnUnauthorizedCode() = runBlocking {
        whenever(service.getSpecificProduct(FAKE_PRODUCT_ID))
            .thenReturn(unauthorizedResponse())

        val result = repository.getSpecificProduct(FAKE_PRODUCT_ID)

        assertEquals(result.status, Status.UNAUTHORIZED)
    }

    @Test
    fun serviceBadRequestCode() = runBlocking {
        whenever(service.getSpecificProduct(FAKE_PRODUCT_ID))
            .thenReturn(badRequestResponse())

        val result = repository.getSpecificProduct(FAKE_PRODUCT_ID)

        assertEquals(result.status, Status.CLIENT_ERROR)
    }

    companion object {
        private const val FAKE_PRODUCT_ID = "MLA-1234"
        private val SUCCESS_RESPONSE = Response.success(getObjectFromJsonResource<Item>("item_response"))
    }
}