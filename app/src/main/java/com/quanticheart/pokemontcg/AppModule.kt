package com.quanticheart.pokemontcg

import com.quanticheart.pokemontcg.presentation.home.listCards.ListPokemonsViewModel
import com.quanticheart.pokemontcg.presentation.details.CardDetailsViewModel
import com.quanticheart.pokemontcg.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModules = module {
    viewModel { SplashViewModel() }
    viewModel { ListPokemonsViewModel(getFirstGenerationPokemonsTcgUseCase = get()) }
    viewModel { CardDetailsViewModel(getPokemonTcgDetailsUseCase = get()) }
}
