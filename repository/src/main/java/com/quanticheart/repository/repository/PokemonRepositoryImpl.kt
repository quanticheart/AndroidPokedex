package com.quanticheart.repository.repository

import com.quanticheart.domain.model.Pokemon
import com.quanticheart.domain.model.PokemonDetails
import com.quanticheart.domain.repository.PokemonRepository
import com.quanticheart.repository.api.PokemonService
import com.quanticheart.repository.mapper.PokemonResponseToDetailsMapper
import com.quanticheart.repository.mapper.PokemonResponseToListMapper

class PokemonRepositoryImpl(private val pokemonService: PokemonService) : PokemonRepository {

    private val pokemonListMapper = PokemonResponseToListMapper()
    private val pokemonDetailsMapper: PokemonResponseToDetailsMapper =
        PokemonResponseToDetailsMapper()

    override suspend fun getPokemons(): Result<List<Pokemon>> {
        return Result.success(
            pokemonListMapper.map(
                pokemonService.getPokemons()
            )
        )
    }

    override suspend fun getPokemon(number: String): Result<PokemonDetails> {
        return Result.success(
            pokemonDetailsMapper.map(pokemonService.getPokemon(number))
        )
    }
}
