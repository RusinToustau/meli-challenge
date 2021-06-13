package com.meli.challenge.views.search.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.meli.challenge.model.Item
import com.meli.challenge.views.search.service.SearchResultService
import java.io.IOException

class ItemPagingSource (
        private val service: SearchResultService,
        private val query: String
) : PagingSource<Int, Item>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {

            val response = service.getItems(query = query, offset = position , limit = params.loadSize)
            val items = response.body()?.results

            val nextKey = if (items.isNullOrEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }

            LoadResult.Page(
                    data = items!!,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = nextKey
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        TODO("Not yet implemented")
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
        private const val NETWORK_PAGE_SIZE = 10
    }
}