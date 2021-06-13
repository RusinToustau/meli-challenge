package com.meli.challenge.views.suggestion.viewmodel

import androidx.lifecycle.viewModelScope
import com.meli.challenge.base.BaseViewModel
import com.meli.challenge.model.SuggestionResponse
import com.meli.challenge.views.suggestion.repository.SuggestionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExplorerViewModel(private val repository: SuggestionRepository) : BaseViewModel<SuggestionResponse>() {

    fun searchItemByName(query:String) = viewModelScope.launch(Dispatchers.Main + coroutineExceptionHandler) {
        isLoading.postValue(true)
        manageResponseStatus(repository.getSuggestedQuery(query))
    }

}