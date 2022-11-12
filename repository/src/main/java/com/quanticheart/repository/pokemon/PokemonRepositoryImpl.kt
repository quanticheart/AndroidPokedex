package com.quanticheart.repository.pokemon

import com.quanticheart.core.extentions.logE
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
                it.data?.logE()
                if (it.status)
                    Result.success(pokemonListMapper.map(it.data!!))
                else
                    Result.failure(Throwable("eeeeee"))
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
                if (it.status)
                    Result.success(pokemonDetailsMapper.map(it.data!!))
                else
                    Result.failure(Throwable(it.message))
            } ?: run {
                Result.failure(Throwable("Erro ao carregar dados"))
            }
        } else {
            Result.failure(Throwable("Erro ao carregar dados"))
        }
    }
}
