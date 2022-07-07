package com.quanticheart.pokemontcg.presentation.listCards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quanticheart.domain.model.Pokemon
import com.quanticheart.domain.model.ViewState
import com.quanticheart.domain.usecase.GetFirstGenerationPokemonsTcgUseCase
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
            kotlin.runCatching {
                getFirstGenerationPokemonsTcgUseCase()
            }.onSuccess {
                _pokemonResult.postValue(ViewState.Success(it.getOrDefault(listOf())))
            }.onFailure {
                _pokemonResult.postValue(ViewState.Failure(it))
            }
        }
    }
}