package com.quanticheart.tcg.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quanticheart.core.extentions.runUseCaseCatching
import com.quanticheart.core.extentions.viewModelScopeLaunch
import com.quanticheart.domain.model.ViewState
import com.quanticheart.domain.model.pokemon.PokemonDetails
import com.quanticheart.domain.usecase.pokemon.DeleteCardCollectionUseCase
import com.quanticheart.domain.usecase.pokemon.GetCardUseCase
import com.quanticheart.domain.usecase.pokemon.GetPokemonTcgDetailsUseCase
import com.quanticheart.domain.usecase.pokemon.InsertCardCollectionUseCase

class CardDetailsViewModel(
    private val getPokemonTcgDetailsUseCase: GetPokemonTcgDetailsUseCase,
    private val insertCollectionUseCase: InsertCardCollectionUseCase,
    private val selectCardUseCase: GetCardUseCase,
    private val deleteCardUseCase: DeleteCardCollectionUseCase,
) : ViewModel() {

    private lateinit var pokemon: PokemonDetails

    private val _pokemonResult = MutableLiveData<ViewState<PokemonDetails>>()
    val pokemonResult: LiveData<ViewState<PokemonDetails>> = _pokemonResult

    private val _collectionUpdate = MutableLiveData<ViewState<Unit>>()
    val collectionUpdate: LiveData<ViewState<Unit>> = _collectionUpdate

    private val _statusCollection = MutableLiveData<Boolean>()
    val statusCollection: LiveData<Boolean> = _statusCollection

    private val _reloadCollectionList = MutableLiveData<Unit>()
    val reloadCollectionList: LiveData<Unit> = _reloadCollectionList

    private val _error = MutableLiveData<Throwable>()
    val errorVerifyCollection: LiveData<Throwable> = _error

    fun getPokemon(id: String) {
        _pokemonResult.postValue(ViewState.Loading)
        viewModelScopeLaunch {
            verifyStatusCollection(id)
            loadPokemon(id)
        }
    }

    private suspend fun verifyStatusCollection(id: String) {
        runUseCaseCatching {
            selectCardUseCase(id)
        }.onSuccess {
            _statusCollection.postValue(it.status)
        }.onFailure {
            _error.postValue(it)
        }
    }

    private suspend fun loadPokemon(id: String) {
        runUseCaseCatching {
            getPokemonTcgDetailsUseCase(id)
        }.onSuccess {
            pokemon = it
            _pokemonResult.postValue(ViewState.Success(it))
        }.onFailure {
            _pokemonResult.postValue(ViewState.Failure(it))
        }
    }

    fun collectionUpdate() {
        _collectionUpdate.postValue(ViewState.Loading)
        viewModelScopeLaunch {
            if (_statusCollection.value == true)
                removeToCollection()
            else
                addToCollection()
        }
    }

    private suspend fun addToCollection() {
        runUseCaseCatching {
            insertCollectionUseCase(pokemon.id, pokemon.name)
        }.onSuccess {
            if (it) _statusCollection.postValue(true)
            _collectionUpdate.postValue(ViewState.Success(Unit))
            _reloadCollectionList.postValue(Unit)
        }.onFailure {
            _collectionUpdate.postValue(ViewState.Failure(it))
        }
    }

    private suspend fun removeToCollection() {
        runUseCaseCatching {
            deleteCardUseCase(pokemon.id, pokemon.name)
        }.onSuccess {
            if (it) _statusCollection.postValue(false)
            _collectionUpdate.postValue(ViewState.Success(Unit))
            _reloadCollectionList.postValue(Unit)
        }.onFailure {
            _collectionUpdate.postValue(ViewState.Failure(it))
        }
    }

}