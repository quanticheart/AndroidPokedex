package com.quanticheart.domain

import com.quanticheart.domain.usecase.pokemon.GetFirstGenerationPokemonsTcgUseCase
import com.quanticheart.domain.usecase.pokemon.GetPokemonTcgDetailsUseCase
import com.quanticheart.domain.usecase.remote.GetDashboardMenuUseCase
import com.quanticheart.domain.usecase.remote.GetMinAppVersionUseCase
import com.quanticheart.domain.usecase.user.*
import org.koin.dsl.module

val domainModules = module {
    factory { GetFirstGenerationPokemonsTcgUseCase(pokemonRepository = get()) }
    factory { GetPokemonTcgDetailsUseCase(pokemonRepository = get()) }

    factory { GetDashboardMenuUseCase(get()) }
    factory { GetSessionUseCase(get()) }
    factory { LoginUseCase(get()) }
    factory { LogoutUseCase(get()) }
    factory { CreateUserUseCase(get()) }
    factory { RecoverPasswordUseCase(get()) }
    factory { GetMinAppVersionUseCase(get()) }
}
