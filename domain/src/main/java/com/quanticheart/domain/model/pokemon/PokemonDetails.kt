package com.quanticheart.domain.model.pokemon

import java.io.Serializable

data class PokemonDetails(
    val id: String,
    val name: String,
    val imageURL: String,
    val description: String,
) : Serializable