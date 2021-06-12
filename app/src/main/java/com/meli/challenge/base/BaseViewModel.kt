package com.meli.challenge.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meli.challenge.core.basemodel.Status.SUCCESS
import com.meli.challenge.core.basemodel.Result
import com.meli.challenge.core.basemodel.Status.CLIENT_ERROR
import com.meli.challenge.core.basemodel.Status.SERVER_ERROR
import com.meli.challenge.core.basemodel.Status.UNAUTHORIZED
import com.meli.challenge.core.basemodel.Status.UNEXPECTED_ERROR
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel<T> : ViewModel() {

    val onSuccessResponse = MutableLiveData<T>()

    val onConnectionError = MutableLiveData<Boolean>()

    val onErrorResponse = MutableLiveData<Boolean>()

    val onClientErrorResponse = MutableLiveData<Boolean>()

    val onUnauthenticatedResponse = MutableLiveData<Any?>()

    val onUnexpectedResponse = MutableLiveData<Any?>()

    val isLoading = MutableLiveData<Boolean>()

    protected val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        onConnectionError.postValue(true)
    }

    fun manageResponseStatus(response: Result<T>) {
        if (response.status == SUCCESS) {
            isLoading.postValue(false)
            response.data?.let { onSuccessResponse.postValue(it) }
                ?: run { onUnexpectedResponse.postValue(Any()) }
        } else {
            manageErrorStatus(response.status)
        }
    }

    private fun manageErrorStatus(status: String) {
        isLoading.postValue(false)
        when (status) {
            UNAUTHORIZED -> {
                onUnauthenticatedResponse.postValue(null)
            }
            CLIENT_ERROR -> {
                onClientErrorResponse.postValue(true)
            }
            SERVER_ERROR -> {
                onErrorResponse.postValue(true)
            }
            UNEXPECTED_ERROR -> {
                onUnexpectedResponse.postValue(null)
            }
        }
    }
}