package com.quanticheart.domain

import com.quanticheart.domain.usecase.GetFirstGenerationPokemonsTcgUseCase
import com.quanticheart.domain.usecase.GetPokemonTcgDetailsUseCase
import org.koin.dsl.module

val domainModules = module {
    factory { GetFirstGenerationPokemonsTcgUseCase(pokemonRepository = get()) }
    factory { GetPokemonTcgDetailsUseCase(pokemonRepository = get()) }
}
