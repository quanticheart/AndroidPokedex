package com.quanticheart.tcg.presentation.main.listCards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quanticheart.core.extentions.runUseCaseCatching
import com.quanticheart.core.extentions.viewModelScopeLaunch
import com.quanticheart.domain.model.ViewState
import com.quanticheart.domain.model.pokemon.Pokemon
import com.quanticheart.domain.usecase.pokemon.GetFirstGenerationPokemonsTcgUseCase

class ListPokemonsViewModel(
    val getFirstGenerationPokemonsTcgUseCase: GetFirstGenerationPokemonsTcgUseCase
) : ViewModel() {

    private val _pokemonResult = MutableLiveData<ViewState<List<Pokemon>>>()
    val pokemonResult: LiveData<ViewState<List<Pokemon>>> = _pokemonResult

    fun getPokemons() {
        _pokemonResult.postValue(ViewState.Loading)
        viewModelScopeLaunch {
            runUseCaseCatching {
                getFirstGenerationPokemonsTcgUseCase()
            }.onSuccess {
                _pokemonResult.postValue(ViewState.Success(it))
            }.onFailure {
                _pokemonResult.postValue(ViewState.Failure(it))
            }
        }
    }
}