package com.quanticheart.pokemontcg.di

import com.quanticheart.pokemontcg.presentation.listCards.ListPokemonsViewModel
import com.quanticheart.pokemontcg.presentation.details.CardDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModules = module {
    viewModel { ListPokemonsViewModel(getFirstGenerationPokemonsTcgUseCase = get()) }
    viewModel { CardDetailsViewModel(getPokemonTcgDetailsUseCase = get()) }
}
