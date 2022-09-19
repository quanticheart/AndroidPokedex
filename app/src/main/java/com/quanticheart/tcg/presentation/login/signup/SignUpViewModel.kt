package com.quanticheart.tcg.presentation.login.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.quanticheart.core.base.BaseViewModel
import com.quanticheart.core.extentions.runUseCaseCatching
import com.quanticheart.core.extentions.viewModelScopeLaunch
import com.quanticheart.domain.model.user.NewUser
import com.quanticheart.domain.usecase.user.CreateUserUseCase

class SignUpViewModel(private val createUserUseCase: CreateUserUseCase) : BaseViewModel() {

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success

    fun create(name: String, email: String, password: String) {
        showLoad()
        viewModelScopeLaunch {
            runUseCaseCatching {
                createUserUseCase(
                    NewUser(
                        name,
                        email,
                        password
                    )
                )
            }.onSuccess {
                _success.postValue(true)
            }.onFailure {
                it.message.showError()
            }
            hideLoad()
        }
    }
}
