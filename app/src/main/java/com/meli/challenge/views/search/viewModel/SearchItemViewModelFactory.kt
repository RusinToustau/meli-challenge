package com.meli.challenge.views.search.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.meli.challenge.views.search.repository.SearchItemRepository

@Suppress("UNCHECKED_CAST")
class SearchItemViewModelFactory (private val repository: SearchItemRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        SearchItemViewModel(repository) as T
}