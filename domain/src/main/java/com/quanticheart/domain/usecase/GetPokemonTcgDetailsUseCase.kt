package com.quanticheart.domain.usecase

import com.quanticheart.domain.repository.PokemonRepository

class GetPokemonTcgDetailsUseCase(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke(number: String) = pokemonRepository.getPokemon(number)
}