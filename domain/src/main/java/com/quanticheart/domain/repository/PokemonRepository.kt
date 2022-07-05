package com.quanticheart.domain.repository

import com.quanticheart.domain.model.Pokemon

interface PokemonRepository {

    suspend fun getPokemons(
        size: Int,
        sort: String
    ): Result<List<Pokemon>>

    suspend fun getPokemon(number: String): Result<Pokemon>

    suspend fun update(pokemon: Pokemon): Result<Pokemon>
}