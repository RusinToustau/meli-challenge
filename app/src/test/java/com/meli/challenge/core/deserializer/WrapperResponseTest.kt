package com.meli.challenge.core.deserializer

import org.junit.Assert
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.meli.challenge.core.basemodel.Result
import com.meli.challenge.core.basemodel.Status
import java.net.HttpURLConnection.HTTP_INTERNAL_ERROR
import java.net.HttpURLConnection.HTTP_BAD_REQUEST
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

@RunWith(JUnit4::class)
class WrapperResponseTest {

    @Test
    fun handleUnauthorizedResponse() {

        val response: Response<Any> = Response.error(HTTP_UNAUTHORIZED, EMPTY_RESPONSE.toResponseBody())
        val result: Result<Any> = WrapperResponse.mapResponse(response)
        Assert.assertEquals(result.status, Status.UNAUTHORIZED)

    }

    @Test
    fun handleBadRequestResponse() {

        val response: Response<Any> = Response.error(HTTP_BAD_REQUEST, EMPTY_RESPONSE.toResponseBody())
        val result: Result<Any> = WrapperResponse.mapResponse(response)
        Assert.assertEquals(result.status, Status.CLIENT_ERROR)

    }

    @Test
    fun handleServerError() {

        val response: Response<Any> = Response.error(HTTP_INTERNAL_ERROR, EMPTY_RESPONSE.toResponseBody())
        val result: Result<Any> = WrapperResponse.mapResponse(response)
        Assert.assertEquals(result.status, Status.SERVER_ERROR)

    }

    @Test
    fun handleSuccessUseCase() {

        val response: Response<FakeDataClass> = Response.success(SUCCESS_RESPONSE)
        val result: Result<FakeDataClass> = WrapperResponse.mapResponse(response)
        Assert.assertEquals(result.status, Status.SUCCESS)
        Assert.assertEquals(result.data?.id, 15)

    }

    companion object {
        private const val EMPTY_RESPONSE = ""
        private val SUCCESS_RESPONSE = FakeDataClass(id = 15)
    }

}

data class FakeDataClass(
        val id: Int
)