package com.quanticheart.domain.repository

import com.quanticheart.domain.model.Pokemon
import com.quanticheart.domain.model.PokemonDetails

interface PokemonRepository {
    suspend fun getPokemons(): Result<List<Pokemon>>

    suspend fun getPokemon(number: String): Result<PokemonDetails>
}