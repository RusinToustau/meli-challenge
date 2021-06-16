package com.utils

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.net.HttpURLConnection.HTTP_BAD_REQUEST
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

object ErrorResponses {

    private val TYPE = "application/json; charset=utf8".toMediaType()
    private val BODY = "".toResponseBody(TYPE)

    fun <T>unauthorizedResponse(): Response<T> = Response.error(HTTP_UNAUTHORIZED, BODY)

    fun <T>badRequestResponse(): Response<T> = Response.error(HTTP_BAD_REQUEST, BODY)
}