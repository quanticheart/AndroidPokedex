package com.quanticheart.tcg.presentation.login.recover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.quanticheart.core.base.viewModel.BaseViewModel
import com.quanticheart.core.extentions.runUseCaseCatching
import com.quanticheart.core.extentions.viewModelScopeLaunch
import com.quanticheart.domain.usecase.user.RecoverPasswordUseCase

class RecoverViewModel(
    private val recoverPasswordUseCase: RecoverPasswordUseCase
) : BaseViewModel() {

    private val _success = MutableLiveData<String>()
    val success: LiveData<String> = _success

    fun recover(email: String) {
        showLoad()
        viewModelScopeLaunch {
            runUseCaseCatching {
                recoverPasswordUseCase(email)
            }.onSuccess {
                _success.postValue(it)
            }.onFailure {
                it.message.showError()
            }
            hideLoad()
        }
    }
}
