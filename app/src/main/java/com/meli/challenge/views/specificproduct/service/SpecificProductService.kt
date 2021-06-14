package com.meli.challenge.views.specificproduct.service

import com.meli.challenge.model.SuggestionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SpecificProductService {

    @GET("products/{productId}")
    suspend fun getSuggest(@Path("productId") productId: String): Response<SuggestionResponse>
}