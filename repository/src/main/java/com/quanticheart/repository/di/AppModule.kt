package com.quanticheart.repository.di

import com.quanticheart.domain.repository.PokemonRepository
import com.quanticheart.repository.api.PokemonService
import com.quanticheart.repository.picasso.PicassoClient
import com.quanticheart.repository.repository.PokemonRepositoryImpl
import com.quanticheart.repository.retrofit.HttpClient
import com.quanticheart.repository.retrofit.RetrofitClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private val othersModules = module {
    single { PicassoClient(application = androidContext()).newInstance() }
}

private val connectionModule = module {
    single { RetrofitClient(application = androidContext()).newInstance() }
    single { HttpClient(get()) }
}

private val repositoryModule = module {
    factory<PokemonRepository> { PokemonRepositoryImpl(pokemonService = get()) }
}

private val serviceModule = module {
    factory { get<HttpClient>().create(PokemonService::class.java) }
}

val dataModules = listOf(
    othersModules,
    connectionModule,
    repositoryModule,
    serviceModule
)
