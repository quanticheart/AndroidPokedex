package com.quanticheart.tcg.presentation.home.listCards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quanticheart.domain.model.ViewState
import com.quanticheart.domain.model.pokemon.Pokemon
import com.quanticheart.domain.usecase.pokemon.GetFirstGenerationPokemonsTcgUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListPokemonsViewModel(
    val getFirstGenerationPokemonsTcgUseCase: GetFirstGenerationPokemonsTcgUseCase
) : ViewModel() {

    private val _pokemonResult = MutableLiveData<ViewState<List<Pokemon>>>()

    val pokemonResult: LiveData<ViewState<List<Pokemon>>>
        get() = _pokemonResult

    fun getPokemons() {
        _pokemonResult.postValue(ViewState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                getFirstGenerationPokemonsTcgUseCase()
            }.onSuccess {
                _pokemonResult.postValue(ViewState.Success(it.getOrDefault(listOf())))
            }.onFailure {
                _pokemonResult.postValue(ViewState.Failure(it))
            }
        }
    }
}