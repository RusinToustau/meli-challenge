package com.meli.challenge.views.suggestion.repository

import com.meli.challenge.core.basemodel.Status
import com.meli.challenge.model.SuggestionResponse
import com.meli.challenge.views.suggestion.service.SuggestionService
import com.utils.ErrorResponses
import com.utils.TestUtils
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class SuggestionRepositoryTest {
    @Mock
    private lateinit var service: SuggestionService

    private lateinit var repository: SuggestionRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = SuggestionRepositoryImpl(service)
    }

    @Test
    fun serviceReturnSuccessResponse() = runBlocking {
        Mockito.`when`(service.getSuggest(query = QUERY_EXAMPLE))
            .thenReturn(Response.success(SUCCESS_RESPONSE))

        val result = repository.getSuggestedQuery(query = QUERY_EXAMPLE)

        Assert.assertEquals(result.status, Status.SUCCESS)
        Assert.assertEquals(result.data, SUCCESS_RESPONSE)
    }

    @Test
    fun serviceReturnAnUnauthorizedCode() = runBlocking {
        Mockito.`when`(service.getSuggest(query = QUERY_EXAMPLE))
            .thenReturn(ErrorResponses.unauthorizedResponse())

        val result = repository.getSuggestedQuery(query = QUERY_EXAMPLE)

        junit.framework.Assert.assertEquals(result.status, Status.UNAUTHORIZED)
    }

    @Test
    fun serviceBadRequestCode() = runBlocking {
        Mockito.`when`(service.getSuggest(query = QUERY_EXAMPLE))
            .thenReturn(ErrorResponses.badRequestResponse())

        val result = repository.getSuggestedQuery(query = QUERY_EXAMPLE)

        junit.framework.Assert.assertEquals(result.status, Status.CLIENT_ERROR)
    }

    companion object {
        const val QUERY_EXAMPLE = "ojotas"
        private val SUCCESS_RESPONSE =
            TestUtils.getObjectFromJsonResource<SuggestionResponse>("suggestion_response")
    }
}