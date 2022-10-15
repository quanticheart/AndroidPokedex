package com.quanticheart.tcg.presentation.login.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.quanticheart.core.base.viewModel.BaseViewModel
import com.quanticheart.core.extentions.runUseCaseCatching
import com.quanticheart.core.extentions.viewModelScopeLaunch
import com.quanticheart.domain.model.user.Credentials
import com.quanticheart.domain.usecase.user.LoginUseCase
import com.quanticheart.domain.usecase.user.RecoverPasswordUseCase

class LoginViewModel(
    private val recoverPasswordUseCase: RecoverPasswordUseCase,
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success

    fun doLogin(email: String, password: String) {
        showLoad()
        viewModelScopeLaunch {
            runUseCaseCatching {
                loginUseCase(Credentials(email, password))
            }.onSuccess {
                _success.postValue(true)
            }.onFailure {
                it.message.showError()
            }
            hideLoad()
        }
    }

    fun recover(email: String) {
        showLoad()
        viewModelScopeLaunch {
            runUseCaseCatching {
                recoverPasswordUseCase(email)
            }.onSuccess {
                _success.postValue(true)
            }.onFailure {
                it.message.showError()
            }
            hideLoad()
        }
    }
}
