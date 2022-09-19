package com.quanticheart.tcg

import com.quanticheart.tcg.presentation.details.CardDetailsViewModel
import com.quanticheart.tcg.presentation.home.listCards.ListPokemonsViewModel
import com.quanticheart.tcg.presentation.login.login.LoginViewModel
import com.quanticheart.tcg.presentation.login.signup.SignUpViewModel
import com.quanticheart.tcg.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModules = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { SignUpViewModel(get()) }

    viewModel { ListPokemonsViewModel(getFirstGenerationPokemonsTcgUseCase = get()) }
    viewModel { CardDetailsViewModel(getPokemonTcgDetailsUseCase = get()) }
}
