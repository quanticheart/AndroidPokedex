package com.quanticheart.domain

import com.quanticheart.domain.usecase.pokemon.GetFirstGenerationPokemonsTcgUseCase
import com.quanticheart.domain.usecase.pokemon.GetPokemonTcgDetailsUseCase
import com.quanticheart.domain.usecase.remote.GetDashboardMenuUseCase
import com.quanticheart.domain.usecase.remote.GetMinAppVersionUseCase
import com.quanticheart.domain.usecase.user.CreateUserUseCase
import com.quanticheart.domain.usecase.user.GetSessionUseCase
import com.quanticheart.domain.usecase.user.LoginUseCase
import com.quanticheart.domain.usecase.user.RecoverPasswordUseCase
import org.koin.dsl.module

val domainModules = module {
    factory { GetFirstGenerationPokemonsTcgUseCase(pokemonRepository = get()) }
    factory { GetPokemonTcgDetailsUseCase(pokemonRepository = get()) }

    factory { GetDashboardMenuUseCase(get()) }
    factory { GetSessionUseCase(get()) }
    factory { LoginUseCase(get()) }
    factory { CreateUserUseCase(get()) }
    factory { RecoverPasswordUseCase(get()) }
    factory { GetMinAppVersionUseCase(get()) }
}
