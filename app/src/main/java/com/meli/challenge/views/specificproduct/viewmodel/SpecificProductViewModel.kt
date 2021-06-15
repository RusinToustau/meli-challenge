package com.meli.challenge.views.specificproduct.viewmodel

import androidx.lifecycle.viewModelScope
import com.meli.challenge.base.BaseViewModel
import com.meli.challenge.model.Item
import com.meli.challenge.views.specificproduct.repository.SpecificProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpecificProductViewModel(private val repository: SpecificProductRepository) : BaseViewModel<Item>() {

    fun findSpecificProduct(id: String) = viewModelScope.launch(Dispatchers.Main + coroutineExceptionHandler) {
        isLoading.postValue(true)
        manageResponseStatus(repository.getSpecificProduct(id))
    }

}