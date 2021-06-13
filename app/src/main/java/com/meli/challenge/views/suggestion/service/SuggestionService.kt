package com.meli.challenge.views.suggestion.service

import com.meli.challenge.model.BaseListResponse
import com.meli.challenge.model.Item
import com.meli.challenge.model.SuggestionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SuggestionService {

    @GET("autosuggest")
    suspend fun getSuggest(
            @Query("showFilters") showFilter: Boolean? = true,
            @Query("limit") limit: Int = 6,
            @Query("api_version") version: Int = 2,
            @Query("q") query: String,
    ): Response<SuggestionResponse>

}