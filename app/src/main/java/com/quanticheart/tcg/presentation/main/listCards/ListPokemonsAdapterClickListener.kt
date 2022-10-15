package com.quanticheart.tcg.presentation.main.listCards

import com.quanticheart.domain.model.pokemon.Pokemon

interface ListPokemonsAdapterClickListener {
    fun click(pokemon: Pokemon)
    fun longClick(pokemon: Pokemon)
}