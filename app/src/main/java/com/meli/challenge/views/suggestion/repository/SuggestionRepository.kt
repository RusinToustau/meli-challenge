package com.meli.challenge.views.suggestion.repository

import com.meli.challenge.model.SuggestionResponse
import com.meli.challenge.core.basemodel.Result

interface SuggestionRepository {

    suspend fun getSuggestedQuery(query: String): Result<SuggestionResponse>

}