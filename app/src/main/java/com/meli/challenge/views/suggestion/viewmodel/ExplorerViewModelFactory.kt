package com.meli.challenge.views.suggestion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.meli.challenge.views.suggestion.repository.SuggestionRepository

@Suppress("UNCHECKED_CAST")
class ExplorerViewModelFactory (private val repository: SuggestionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            ExplorerViewModel(repository) as T
}