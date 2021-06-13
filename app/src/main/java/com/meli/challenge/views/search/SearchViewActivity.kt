package com.meli.challenge.views.search

import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.meli.challenge.base.BaseActivity
import com.meli.challenge.base.BaseViewModel
import com.meli.challenge.core.api.ApiClient
import com.meli.challenge.databinding.ActivitySearchItemBinding
import com.meli.challenge.model.Item
import com.meli.challenge.views.search.adapter.SearchItemAdapter
import com.meli.challenge.views.search.repository.SearchItemRepository
import com.meli.challenge.views.search.repository.SearchItemRepositoryImpl
import com.meli.challenge.views.search.service.SearchResultService
import com.meli.challenge.views.search.viewModel.SearchItemViewModel
import com.meli.challenge.views.search.viewModel.SearchItemViewModelFactory

class SearchViewActivity :  BaseActivity<List<Item>,ActivitySearchItemBinding>() {

    private val service: SearchResultService = ApiClient.retrofit.create(SearchResultService::class.java)
    private val repository: SearchItemRepository = SearchItemRepositoryImpl(service)
    private val viewModel: SearchItemViewModel by viewModels { SearchItemViewModelFactory(repository) }
    private val adapter = SearchItemAdapter(ArrayList())
    private var name: String = ""

    override fun onStart() {
        super.onStart()
        binding.resultList.adapter = adapter
        addObservers()
    }

    override fun getViewBinding() = ActivitySearchItemBinding.inflate(layoutInflater)

    override fun getViewContainer(): Int = binding.viewContainer.id

    override fun getLoadingView(): View = binding.loadingView.root

    override fun getViewModel(): BaseViewModel<List<Item>> = viewModel

    override fun getRetryAction(): (() -> Unit) = { getServerDataProvider() }

    private fun getServerDataProvider() = viewModel.searchItemByName(name)

    override fun addOnSuccessObserver() {
        viewModel.onSuccessResponse.observe(
                this,
                Observer {
                    result -> adapter.setList(result)
                })
    }

}