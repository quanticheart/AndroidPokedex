package com.quanticheart.tcg.presentation.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quanticheart.core.extentions.runUseCaseCatching
import com.quanticheart.core.extentions.viewModelScopeLaunch
import com.quanticheart.domain.model.ViewState
import com.quanticheart.domain.model.remote.TcgRemote
import com.quanticheart.domain.usecase.remote.GetTcgRemoteUseCase

class AboutViewModel(
    private val getTcgRemoteUseCase: GetTcgRemoteUseCase
) : ViewModel() {

    private val _details = MutableLiveData<ViewState<TcgRemote>>()
    val details: LiveData<ViewState<TcgRemote>> = _details

    fun getDetails() {
        viewModelScopeLaunch {
            _details.postValue(ViewState.Loading)
            runUseCaseCatching {
                getTcgRemoteUseCase()
            }.onSuccess {
                _details.postValue(ViewState.Success(it))
            }.onFailure {
                _details.postValue(ViewState.Failure(it))
            }
        }
    }
}