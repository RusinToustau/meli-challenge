package com.meli.challenge.views.search.viewModel

import com.meli.challenge.base.BaseViewModel
import com.meli.challenge.model.Item
import com.meli.challenge.views.search.repository.SearchItemRepository

class SearchItemViewModel(private val repository: SearchItemRepository) : BaseViewModel<List<Item>>() {

    fun searchItemByName(name:String) {

    }
}