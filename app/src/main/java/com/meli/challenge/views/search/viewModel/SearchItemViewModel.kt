package com.meli.challenge.views.search.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.meli.challenge.base.BaseViewModel
import com.meli.challenge.model.Item
import com.meli.challenge.views.search.repository.SearchItemRepository
import kotlinx.coroutines.flow.Flow

class SearchItemViewModel(private val repository: SearchItemRepository) : ViewModel() {

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Item>>? = null

    fun searchItemByName(name:String) : Flow<PagingData<Item>> {
        val lastResult = currentSearchResult
        if (name == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = name
        val newResult: Flow<PagingData<Item>> = repository.getSearchResultStream(name)
                .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

}