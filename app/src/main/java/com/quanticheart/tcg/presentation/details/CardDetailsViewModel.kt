package com.quanticheart.tcg.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quanticheart.domain.model.ViewState
import com.quanticheart.domain.model.pokemon.Pokemon
import com.quanticheart.domain.model.pokemon.PokemonDetails
import com.quanticheart.domain.usecase.pokemon.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardDetailsViewModel(
    private val getPokemonTcgDetailsUseCase: GetPokemonTcgDetailsUseCase,
    private val collectionUseCase: InsertCardCollectionUseCase,
    private val allcollectionUseCase: GetCardCollectionUseCase,
    private val useCase: GetCardUseCase,
    private val delete: DeleteCardCollectionUseCase,
) :
    ViewModel() {

    private lateinit var pokemon: Pokemon

    private val _pokemonResult = MutableLiveData<ViewState<PokemonDetails>>()
    val pokemonResult: LiveData<ViewState<PokemonDetails>> = _pokemonResult

    private val _addToCollection = MutableLiveData<ViewState<Unit>>()
    val addToCollection: LiveData<ViewState<Unit>> = _addToCollection

    fun getPokemon(pokemon: Pokemon) {
        this.pokemon = pokemon
        _pokemonResult.postValue(ViewState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                getPokemonTcgDetailsUseCase(pokemon.id)
            }.onSuccess {
                _pokemonResult.postValue(
                    ViewState.Success(it.getOrThrow())
                )
            }.onFailure {
                _pokemonResult.postValue(ViewState.Failure(it))
            }
        }
    }

    fun addToCollection() {
        _addToCollection.postValue(ViewState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                collectionUseCase(pokemon.id, pokemon.name)
//                allcollectionUseCase()
//                useCase("base1-5")
//                delete("base1-4", "Charizard")
            }.onSuccess {
//                _addToCollection.postValue(ViewState.Success(Unit))
            }.onFailure {
//                _addToCollection.postValue(ViewState.Failure(it))
            }
        }
    }
}