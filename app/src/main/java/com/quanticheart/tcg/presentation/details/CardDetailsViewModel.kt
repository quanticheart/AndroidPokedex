package com.quanticheart.tcg.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quanticheart.domain.model.ViewState
import com.quanticheart.domain.model.pokemon.Pokemon
import com.quanticheart.domain.model.pokemon.PokemonDetails
import com.quanticheart.domain.usecase.pokemon.GetPokemonTcgDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardDetailsViewModel(val getPokemonTcgDetailsUseCase: GetPokemonTcgDetailsUseCase) :
    ViewModel() {

    private lateinit var pokemon: Pokemon

    private val _pokemonResult = MutableLiveData<ViewState<PokemonDetails>>()
    val pokemonResult: LiveData<ViewState<PokemonDetails>>
        get() = _pokemonResult

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
}