package com.meli.challenge.views.search.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.meli.challenge.model.Item
import com.meli.challenge.views.search.paging.ItemPagingSource
import com.meli.challenge.views.search.service.SearchResultService
import kotlinx.coroutines.flow.Flow

class SearchItemRepositoryImpl(private val service: SearchResultService): SearchItemRepository {

    override fun getSearchResultStream(query: String): Flow<PagingData<Item>> {
        return Pager(
                config = PagingConfig(
                        pageSize = NETWORK_PAGE_SIZE,
                        enablePlaceholders = false
                ),
                pagingSourceFactory = { ItemPagingSource(service, query) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

}