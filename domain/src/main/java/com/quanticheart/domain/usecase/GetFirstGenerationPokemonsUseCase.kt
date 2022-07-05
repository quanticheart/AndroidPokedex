package com.quanticheart.domain.usecase

import com.quanticheart.domain.repository.PokemonRepository

class GetFirstGenerationPokemonsUseCase (private val pokemonRepository: PokemonRepository) {

    suspend operator fun invoke() = pokemonRepository.getPokemons(150, "number,asc")

}

