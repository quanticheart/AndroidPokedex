package com.quanticheart.domain

import com.quanticheart.domain.usecase.pokemon.*
import com.quanticheart.domain.usecase.remote.GetDashboardMenuUseCase
import com.quanticheart.domain.usecase.remote.GetMinAppVersionUseCase
import com.quanticheart.domain.usecase.user.*
import org.koin.dsl.module

val domainModules = module {
    factory { GetFirstGenerationPokemonsTcgUseCase(pokemonRepository = get()) }
    factory { GetPokemonTcgDetailsUseCase(pokemonRepository = get()) }
    factory { InsertCardCollectionUseCase(repository = get()) }
    factory { DeleteCardCollectionUseCase(repository = get()) }
    factory { GetCardCollectionUseCase(repository = get()) }
    factory { GetCardUseCase(repository = get()) }

    factory { GetDashboardMenuUseCase(get()) }
    factory { GetSessionUseCase(get()) }
    factory { LoginUseCase(get()) }
    factory { LogoutUseCase(get()) }
    factory { CreateUserUseCase(get()) }
    factory { RecoverPasswordUseCase(get()) }
    factory { GetMinAppVersionUseCase(get()) }
}
