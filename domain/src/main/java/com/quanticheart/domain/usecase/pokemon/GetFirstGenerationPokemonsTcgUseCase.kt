package com.quanticheart.domain.usecase.pokemon

import com.quanticheart.domain.repository.PokemonRepository

class GetFirstGenerationPokemonsTcgUseCase(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke() = pokemonRepository.getPokemons()
}

