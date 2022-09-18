package com.quanticheart.repository.pokemon

import com.quanticheart.domain.model.pokemon.Pokemon
import com.quanticheart.domain.model.pokemon.PokemonDetails
import com.quanticheart.domain.repository.PokemonRepository
import com.quanticheart.repository.pokemon.mapper.PokemonResponseToDetailsMapper
import com.quanticheart.repository.pokemon.mapper.PokemonResponseToListMapper

class PokemonRepositoryImpl(private val pokemonService: PokemonEndPoints) : PokemonRepository {

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
