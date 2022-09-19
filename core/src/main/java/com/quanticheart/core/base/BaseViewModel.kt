@file:Suppress("MemberVisibilityCanBePrivate")

package com.quanticheart.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    protected fun showLoad() {
        _loading.postValue(true)
    }

    protected fun hideLoad() {
        _loading.postValue(false)
    }

    protected fun String?.showError() {
        _error.postValue(this ?: "Error")
    }

    fun consumedError() {
        _error.value = null
    }
}
