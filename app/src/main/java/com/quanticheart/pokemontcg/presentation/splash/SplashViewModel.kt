package com.quanticheart.pokemontcg.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//
// Created by Jonn Alves on 18/09/22.
//
class SplashViewModel : ViewModel() {

    private val _offline = MutableLiveData<Boolean>()
    val offline: LiveData<Boolean> = _offline

    private val _online = MutableLiveData<Boolean>()
    val online: LiveData<Boolean> = _online

    fun startSessionVerification() {}
}