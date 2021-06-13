package com.meli.challenge.views.search.service

import com.meli.challenge.model.BaseListResponse
import com.meli.challenge.model.Item
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchResultService {

    @GET("search")
    suspend fun getItems(
            @Query("offset") offset: Int,
            @Query("offset") limit: Int,
    ): Response<BaseListResponse<Item>>

}