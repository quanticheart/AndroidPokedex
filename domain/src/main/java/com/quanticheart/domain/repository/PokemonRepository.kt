package com.quanticheart.domain.repository

import com.quanticheart.domain.model.pokemon.Pokemon
import com.quanticheart.domain.model.pokemon.PokemonDetails

interface PokemonRepository {
    suspend fun getPokemons(): Result<List<Pokemon>>

    suspend fun getPokemon(number: String): Result<PokemonDetails>
}