package com.meli.challenge.views.suggestion

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.meli.challenge.base.BaseActivity
import com.meli.challenge.base.BaseViewModel
import com.meli.challenge.core.api.ApiClient
import com.meli.challenge.databinding.ExplorerActivityBinding
import com.meli.challenge.extensions.openActivity
import com.meli.challenge.model.SuggestionResponse
import com.meli.challenge.views.search.SearchViewActivity
import com.meli.challenge.views.search.SearchViewActivity.Companion.QUERY_KEY
import com.meli.challenge.views.suggestion.adapter.SuggestionAdapter
import com.meli.challenge.views.suggestion.repository.SuggestionRepository
import com.meli.challenge.views.suggestion.repository.SuggestionRepositoryImpl
import com.meli.challenge.views.suggestion.service.SuggestionService
import com.meli.challenge.views.suggestion.viewmodel.ExplorerViewModel
import com.meli.challenge.views.suggestion.viewmodel.ExplorerViewModelFactory

class ExplorerActivity :  BaseActivity<SuggestionResponse,ExplorerActivityBinding>(),
        SuggestionAdapter.Listener {

    private val service: SuggestionService = ApiClient.retrofit.create(SuggestionService::class.java)
    private val repository: SuggestionRepository = SuggestionRepositoryImpl(service)
    private val viewModel: ExplorerViewModel by viewModels { ExplorerViewModelFactory(repository) }
    private val adapter = SuggestionAdapter(ArrayList(),this@ExplorerActivity)
    private var currentQuery: String = ""

    override fun onStart() {
        super.onStart()
        initAdapter()
        addObservers()
        initTextWatcher()
    }

    private fun  initTextWatcher() {
        binding.searchItem.addTextChangedListener( object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { /*** DO NOTHING ***/ }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { updateRepoListFromInput() }

            override fun afterTextChanged(p0: Editable?) {  /*** DO NOTHING ***/ }
        })
    }

    private fun updateRepoListFromInput() {
        binding.searchItem.text.trim().let {
            if (it.isNotEmpty())
                search(it.toString())
            else
                adapter.setList(ArrayList())
        }
    }

    private fun initAdapter() {
        binding.resultList.adapter = adapter
    }

    override fun getViewBinding() = ExplorerActivityBinding.inflate(layoutInflater).apply {
        closeButton.setOnClickListener { searchItem.setText("") }
    }

    override fun getViewContainer(): Int = binding.viewContainer.id

    override fun getLoadingView(): View = binding.loadingView.root

    override fun getViewModel(): BaseViewModel<SuggestionResponse> = viewModel

    override fun onUserClickSuggestion(query: String?) =
            openActivity(SearchViewActivity::class.java) { putString(QUERY_KEY, query) }

    override fun replaceQueryParameter(query: String?) = binding.searchItem.setText(query)

    override fun getRetryAction(): (() -> Unit) = { search(currentQuery) }

    private fun search(query: String) {
        currentQuery = query
        viewModel.searchItemByName(query)
    }

    override fun addOnSuccessObserver() =
        viewModel.onSuccessResponse.observe(
                this,
                Observer { suggestionResponse ->
                    suggestionResponse.suggestedQueries?.let { adapter.setList(it) }
                })

}