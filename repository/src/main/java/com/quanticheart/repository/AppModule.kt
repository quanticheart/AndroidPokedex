package com.quanticheart.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.quanticheart.domain.repository.AppRepository
import com.quanticheart.domain.repository.PokemonRepository
import com.quanticheart.domain.repository.UserRepository
import com.quanticheart.repository.base.retrofit.HttpClient
import com.quanticheart.repository.base.retrofit.RetrofitClient
import com.quanticheart.repository.picasso.PicassoClient
import com.quanticheart.repository.pokemon.PokemonEndPoints
import com.quanticheart.repository.pokemon.PokemonRepositoryImpl
import com.quanticheart.repository.remoteFeatures.AppRepositoryImpl
import com.quanticheart.repository.user.UserRepositoryImpl
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
    factory { Firebase.auth }
    factory { Firebase.firestore }

    factory<AppRepository> { AppRepositoryImpl() }
    factory<UserRepository> { UserRepositoryImpl(get(), get(), get()) }
    factory<PokemonRepository> { PokemonRepositoryImpl(pokemonService = get()) }
}

private val serviceModule = module {
    factory { get<HttpClient>().create(PokemonEndPoints::class.java) }
}

val dataModules = listOf(
    othersModules,
    connectionModule,
    repositoryModule,
    serviceModule
)
