package com.meli.challenge.views.search

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.meli.challenge.R
import com.meli.challenge.base.GenericErrorFragment
import com.meli.challenge.core.api.ApiClient
import com.meli.challenge.databinding.ActivitySearchItemBinding
import com.meli.challenge.extensions.setToolbar
import com.meli.challenge.extensions.showFragment
import com.meli.challenge.views.search.adapter.SearchItemPagingAdapter
import com.meli.challenge.views.search.repository.SearchItemRepository
import com.meli.challenge.views.search.repository.SearchItemRepositoryImpl
import com.meli.challenge.views.search.service.SearchResultService
import com.meli.challenge.views.search.viewModel.SearchItemViewModel
import com.meli.challenge.views.search.viewModel.SearchItemViewModelFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchViewActivity : AppCompatActivity() {

    private val service: SearchResultService = ApiClient.retrofit.create(SearchResultService::class.java)
    private val repository: SearchItemRepository = SearchItemRepositoryImpl(service)
    private val viewModel: SearchItemViewModel by viewModels { SearchItemViewModelFactory(repository) }
    private val adapter = SearchItemPagingAdapter()
    private var currentQuery: String = ""

    private var searchJob: Job? = null

    private lateinit var binding: ActivitySearchItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchItemBinding.inflate(layoutInflater).apply {
            setContentView(root)
            setToolbar(simpleToolbar.toolbar)
        }

        initAdapter()
        intent?.extras?.getString(QUERY_KEY)?.let { query -> currentQuery = query }
        search()
    }

    private fun initAdapter() {
        binding.resultList.adapter = adapter
    }

    private fun search() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch(coroutineExceptionHandler) {
            viewModel.searchItemByName(currentQuery).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        showGenericError()
    }

    private fun showGenericError() {
        showFragment(
            GenericErrorFragment.newInstance(
                errorTitle = getString(R.string.error_title),
                errorDescription = getString(R.string.error_description),
                primaryButtonText = getString(R.string.retry_button),
                onPrimaryButtonAction = { search() }
            ),
            addToBackStack = true,
            containerViewId = binding.viewContainer.id)
    }

    companion object {
        const val QUERY_KEY = "query_suggested"
    }
}