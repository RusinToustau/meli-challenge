package com.meli.challenge.base

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.meli.challenge.extensions.blockScreen
import com.meli.challenge.extensions.showFragment
import com.meli.challenge.extensions.unBlockScreen
import com.meli.challenge.R

abstract class BaseActivity<T,B : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
    }

    abstract fun getViewBinding(): B
    abstract fun getViewContainer(): Int
    abstract fun getLoadingView(): View
    abstract fun getViewModel(): BaseViewModel<T>
    abstract fun getRetryAction(): (()-> Unit)?

    fun addObservers() {
        addOnSuccessObserver()
        addOnErrorObserver()
        addIsLoadingObserver()
        addOnConnectionErrorObserver()
        addOnClientErrorObserver()
        addOnUnauthenticatedObserver()
        addOnUnexpectedObserver()
    }

    abstract fun addOnSuccessObserver()

    private fun addIsLoadingObserver() {
        (getViewModel() as BaseViewModel<*>).isLoading.observe(this, Observer { isLoading ->
            if (isLoading) {
                getLoadingView().visibility = VISIBLE
            } else {
                getLoadingView().visibility = GONE
            }
        })
    }

    private fun addOnErrorObserver() {
        (getViewModel() as BaseViewModel<*>).onErrorResponse.observe(
            this,
            Observer { isError ->
                if (isError) {
                    showGenericError()
                }
            })
    }

    private fun addOnConnectionErrorObserver() {
        (getViewModel() as BaseViewModel<*>).onConnectionError.observe(
            this,
            Observer { isConnectionError ->
                if (isConnectionError) {
                    showGenericError()
                }
            })
    }

    private fun addOnClientErrorObserver() {
        (getViewModel() as BaseViewModel<*>).onClientErrorResponse.observe(
            this,
            Observer { isError ->
                if (isError) {
                    showGenericError()
                }
            })
    }

    private fun addOnUnauthenticatedObserver() {
        (getViewModel() as BaseViewModel<*>).onUnauthenticatedResponse.observe(
            this,
            Observer {
                showUnauthorizedError()
            })
    }

    private fun addOnUnexpectedObserver() {
        (getViewModel() as BaseViewModel<*>).onUnexpectedResponse.observe(this, Observer {
            showGenericError()
        })
    }

    private fun showGenericError() {
        showFragment(
            GenericErrorFragment.newInstance(
                errorTitle = getString(R.string.error_title),
                errorDescription = getString(R.string.error_description),
                primaryButtonText = getString(R.string.retry_button),
                onPrimaryButtonAction = getRetryAction()
            ),
            addToBackStack = false,
            containerViewId = getViewContainer())
    }

    private fun showUnauthorizedError() {
        showFragment(
            GenericErrorFragment.newInstance(
                errorTitle = getString(R.string.unauthorized_title),
                errorDescription = getString(R.string.unauthorized_description)
            ),
            addToBackStack = false,
            containerViewId = getViewContainer())
    }

}