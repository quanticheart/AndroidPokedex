package com.quanticheart.repository.di

import com.quanticheart.domain.repository.PokemonRepository
import com.quanticheart.repository.api.PokemonService
import com.quanticheart.repository.picasso.PicassoClient
import com.quanticheart.repository.repository.PokemonRepositoryImpl
import com.quanticheart.repository.retrofit.HttpClient
import com.quanticheart.repository.retrofit.RetrofitClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModules = module {
    single { RetrofitClient(application = androidContext()).newInstance() }
    single { HttpClient(get()) }
    factory { get<HttpClient>().create(PokemonService::class.java) }
    single { PicassoClient(application = androidContext()).newInstance() }
}

val dataModules = module {
    factory<PokemonRepository> { PokemonRepositoryImpl(pokemonService = get()) }
}
