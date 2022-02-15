package br.com.heiderlopes.pokemonwstemplatev2.data.remote.repository

import br.com.heiderlopes.pokemonwstemplatev2.data.remote.api.PokemonService
import br.com.heiderlopes.pokemonwstemplatev2.data.remote.mapper.PokemonResponseListToPokemonListMapper
import br.com.heiderlopes.pokemonwstemplatev2.data.remote.mapper.PokemonResponseToPokemonMapper
import br.com.heiderlopes.pokemonwstemplatev2.data.remote.mapper.PokemonToUpdatePokemonRequestMapper
import br.com.heiderlopes.pokemonwstemplatev2.domain.model.Pokemon
import br.com.heiderlopes.pokemonwstemplatev2.domain.repository.PokemonRepository

class PokemonRepositoryImpl(private val pokemonService: PokemonService) : PokemonRepository {

    private val pokemonListMapper = PokemonResponseListToPokemonListMapper()
    private val pokemontMapper: PokemonResponseToPokemonMapper = PokemonResponseToPokemonMapper()
    private val pokemonToUpdatePokemonRequestMapper: PokemonToUpdatePokemonRequestMapper =
        PokemonToUpdatePokemonRequestMapper()

    override suspend fun getPokemons(size: Int, sort: String): Result<List<Pokemon>> {
        return Result.success(
            pokemonListMapper.map(
                pokemonService.getPokemons(
                    size,
                    sort
                ).pokemons
            )
        )
    }

    override suspend fun getPokemon(number: String): Result<Pokemon> {
        return Result.success(
            pokemontMapper.map(pokemonService.getPokemon(number))
        )
    }

    override suspend fun update(pokemon: Pokemon): Result<Pokemon> {
        return Result.success(
            pokemontMapper.map(
                pokemonService.updatePokemon(
                    pokemonToUpdatePokemonRequestMapper.map(pokemon)
                )
            )
        )
    }

}
