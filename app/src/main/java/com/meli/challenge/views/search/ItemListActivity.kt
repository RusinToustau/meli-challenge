package com.meli.challenge.views.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.meli.challenge.core.api.ApiClient
import com.meli.challenge.databinding.ActivitySearchItemBinding
import com.meli.challenge.views.search.adapter.SearchItemPagingAdapter
import com.meli.challenge.views.search.repository.SearchItemRepository
import com.meli.challenge.views.search.repository.SearchItemRepositoryImpl
import com.meli.challenge.views.search.service.SearchResultService
import com.meli.challenge.views.search.viewModel.SearchItemViewModel
import com.meli.challenge.views.search.viewModel.SearchItemViewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ItemListActivity : AppCompatActivity() {

    private val service: SearchResultService = ApiClient.retrofit.create(SearchResultService::class.java)
    private val repository: SearchItemRepository = SearchItemRepositoryImpl(service)
    private val viewModel: SearchItemViewModel by viewModels { SearchItemViewModelFactory(repository) }
    private val adapter = SearchItemPagingAdapter()

    private var searchJob: Job? = null

    private lateinit var binding: ActivitySearchItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initTextWatcher()
    }

    private fun  initTextWatcher() {
        binding.searchItem.addTextChangedListener( object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { /*** DO NOTHING ***/ }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { updateRepoListFromInput() }

            override fun afterTextChanged(p0: Editable?) {  /*** DO NOTHING ***/ }
        })
    }

    private fun initAdapter() {
        binding.resultList.adapter = adapter
    }

    private fun updateRepoListFromInput() {
        binding.searchItem.text.trim().let {
            if (it.isNotEmpty()) {
                search(it.toString())
            }
        }
    }

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchItemByName(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}