package com.meli.challenge.views.specificproduct

import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.meli.challenge.base.BaseActivity
import com.meli.challenge.base.BaseViewModel
import com.meli.challenge.core.api.ApiClient
import com.meli.challenge.databinding.ActivitySpecificProductBinding
import com.meli.challenge.extensions.loadurl
import com.meli.challenge.extensions.setToolbar
import com.meli.challenge.model.Item
import com.meli.challenge.views.specificproduct.repository.SpecificProductRepository
import com.meli.challenge.views.specificproduct.repository.SpecificProductRepositoryImpl
import com.meli.challenge.views.specificproduct.service.SpecificProductService
import com.meli.challenge.views.specificproduct.viewmodel.SpecificProductViewModel
import com.meli.challenge.views.specificproduct.viewmodel.SpecificProductViewModelFactory

class SpecificProductActivity : BaseActivity<Item, ActivitySpecificProductBinding>() {
    private val service: SpecificProductService = ApiClient.retrofit.create(SpecificProductService::class.java)
    private val repository: SpecificProductRepository = SpecificProductRepositoryImpl(service)
    private val viewModel: SpecificProductViewModel by viewModels { SpecificProductViewModelFactory(repository) }
    private var productId: String = ""

    override fun onStart() {
        super.onStart()
        addObservers()
        getProductID()
        setToolbar(binding.toolbar)
    }

    private fun getProductID() = intent?.extras?.getString(PRODUCT_ID)?.let { id ->
            productId = id
            loadProduct()
    }

    private fun loadProduct() = viewModel.findSpecificProduct(productId)

    override fun getViewBinding() = ActivitySpecificProductBinding.inflate(layoutInflater)

    override fun getViewContainer(): Int = binding.viewContainer.id

    override fun getLoadingView(): View = binding.loadingView.root

    override fun getViewModel(): BaseViewModel<Item> = viewModel

    override fun getRetryAction(): () -> Unit = { loadProduct() }

    override fun addOnSuccessObserver() =
            viewModel.onSuccessResponse.observe(this, Observer { item ->
                binding.productImage.loadurl(item.pictures?.get(0)?.url)
                binding.productTitle.text = item.title
            })

    companion object {
        const val PRODUCT_ID = "product_id"
    }
}