package com.quanticheart.tcg.presentation.main.userCards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quanticheart.core.extentions.runUseCaseCatching
import com.quanticheart.core.extentions.viewModelScopeLaunch
import com.quanticheart.domain.model.ViewState
import com.quanticheart.domain.model.pokemon.Card
import com.quanticheart.domain.usecase.pokemon.GetCardCollectionUseCase

class UserCardsViewModel(private val getCardCollectionUseCase: GetCardCollectionUseCase) :
    ViewModel() {

    private val _collection = MutableLiveData<ViewState<List<Card>>>()
    val collection: LiveData<ViewState<List<Card>>> = _collection

    fun loadList() {
        _collection.postValue(ViewState.Loading)
        viewModelScopeLaunch {
            runUseCaseCatching {
                getCardCollectionUseCase()
            }.onSuccess {
                _collection.postValue(ViewState.Success(it))
            }.onFailure {
                _collection.postValue(ViewState.Failure(it))
            }
        }
    }
}