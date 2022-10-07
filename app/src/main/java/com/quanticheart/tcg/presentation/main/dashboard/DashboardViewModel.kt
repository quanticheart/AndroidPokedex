package com.quanticheart.tcg.presentation.main.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quanticheart.core.extentions.runUseCaseCatching
import com.quanticheart.core.extentions.viewModelScopeLaunch
import com.quanticheart.domain.model.ViewState
import com.quanticheart.domain.model.user.User
import com.quanticheart.domain.usecase.user.GetSessionUseCase
import com.quanticheart.domain.usecase.user.LogoutUseCase

class DashboardViewModel(
    private val userUseCase: GetSessionUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _session = MutableLiveData<ViewState<User>>()
    val session: LiveData<ViewState<User>> = _session

    private val _logout = MutableLiveData<ViewState<Unit>>()
    val logout: LiveData<ViewState<Unit>> = _logout

    init {
        _session.postValue(ViewState.Loading)
        viewModelScopeLaunch {
            runUseCaseCatching {
                userUseCase()
            }.onSuccess {
                _session.postValue(ViewState.Success(it))
            }.onFailure {
                _session.postValue(ViewState.Failure(it))
            }
        }
    }

    fun logout() {
        _logout.postValue(ViewState.Loading)
        viewModelScopeLaunch {
            runUseCaseCatching {
                logoutUseCase()
            }.onSuccess {
                _logout.postValue(ViewState.Success(Unit))
            }.onFailure {
                _logout.postValue(ViewState.Failure(it))
            }
        }
    }
}