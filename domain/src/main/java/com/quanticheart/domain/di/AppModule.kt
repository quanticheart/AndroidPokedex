package com.quanticheart.domain.di

import com.quanticheart.domain.usecase.GetFirstGenerationPokemonsUseCase
import com.quanticheart.domain.usecase.GetPokemonUseCase
import com.quanticheart.domain.usecase.UpdatePokemonUseCase
import org.koin.dsl.module

val domainModules = module {
    factory { GetFirstGenerationPokemonsUseCase(pokemonRepository = get()) }
    factory { GetPokemonUseCase(pokemonRepository = get()) }
    factory { UpdatePokemonUseCase(pokemonRepository = get()) }
}
