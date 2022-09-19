package com.quanticheart.core.extentions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//
// Created by Jonn Alves on 18/09/22.
//
fun ViewModel.viewModelScopeLaunch(callback: suspend CoroutineScope. () -> Unit) {
    viewModelScope.launch(Dispatchers.IO) {
        callback()
    }
}

inline fun <T, R> T.runUseCaseCatching(block: T.() -> Result<R>): Result<R> {
    return try {
        block()
    } catch (e: Throwable) {
        Result.failure(e)
    }
}
