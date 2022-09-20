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
        val response = pokemonService.getPokemons()
        return if (response.isSuccessful) {
            response.body()?.let {
                Result.success(pokemonListMapper.map(it))
            } ?: run {
                Result.failure(Throwable("Erro ao carregar dados"))
            }
        } else {
            Result.failure(Throwable("Erro ao carregar dados"))
        }
    }

    override suspend fun getPokemon(number: String): Result<PokemonDetails> {
        val response = pokemonService.getPokemon(number)
        return if (response.isSuccessful) {
            response.body()?.let {
                Result.success(pokemonDetailsMapper.map(it))
            } ?: run {
                Result.failure(Throwable("Erro ao carregar dados"))
            }
        } else {
            Result.failure(Throwable("Erro ao carregar dados"))
        }
    }
}
