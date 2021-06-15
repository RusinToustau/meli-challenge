package com.meli.challenge.views.specificproduct.service

import com.meli.challenge.model.Item
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SpecificProductService {

    @GET("items/{productId}")
    suspend fun getSpecificProduct(@Path("productId") productId: String): Response<Item>
}