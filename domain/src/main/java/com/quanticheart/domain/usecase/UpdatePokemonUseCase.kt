package com.quanticheart.domain.usecase

import com.quanticheart.domain.model.Pokemon
import com.quanticheart.domain.repository.PokemonRepository

class UpdatePokemonUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(pokemon: Pokemon) =
        pokemonRepository.update(
            pokemon
        )
}