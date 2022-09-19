package com.quanticheart.tcg.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quanticheart.core.extentions.runUseCaseCatching
import com.quanticheart.core.extentions.viewModelScopeLaunch
import com.quanticheart.domain.usecase.user.GetSessionUseCase

//
// Created by Jonn Alves on 18/09/22.
//
class SplashViewModel(private val session: GetSessionUseCase) : ViewModel() {

    private val _offline = MutableLiveData<Boolean>()
    val offline: LiveData<Boolean> = _offline

    private val _online = MutableLiveData<Boolean>()
    val online: LiveData<Boolean> = _online

    fun startSessionVerification() {
        viewModelScopeLaunch {
            runUseCaseCatching {
                session()
            }.onSuccess {
                _online.postValue(true)
            }.onFailure {
                _offline.postValue(true)
            }
        }
    }
}