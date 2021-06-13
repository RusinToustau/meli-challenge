package com.meli.challenge.views.suggestion.repository

import com.meli.challenge.core.deserializer.WrapperResponse.mapResponse
import com.meli.challenge.views.suggestion.service.SuggestionService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SuggestionRepositoryImpl(private val service: SuggestionService) : SuggestionRepository {

    override suspend fun getSuggestedQuery(query: String) = withContext(Dispatchers.IO) {
        return@withContext mapResponse(service.getSuggest(query = query))
    }

}