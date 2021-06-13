package com.meli.challenge.model

import com.google.gson.annotations.SerializedName

data class SuggestionResponse(
        val q : String,
        @SerializedName("suggested_queries")
        val suggestedQueries : List<SuggestedQuery>?
)