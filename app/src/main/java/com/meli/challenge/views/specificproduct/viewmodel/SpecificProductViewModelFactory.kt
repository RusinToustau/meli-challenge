package com.meli.challenge.views.specificproduct.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.meli.challenge.views.specificproduct.repository.SpecificProductRepository

@Suppress("UNCHECKED_CAST")
class SpecificProductViewModelFactory (private val repository: SpecificProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            SpecificProductViewModel(repository) as T
}