package com.meli.challenge.views.search.repository

import androidx.paging.PagingData
import com.meli.challenge.model.Item
import kotlinx.coroutines.flow.Flow

interface SearchItemRepository {

    fun getSearchResultStream(query: String): Flow<PagingData<Item>>

}