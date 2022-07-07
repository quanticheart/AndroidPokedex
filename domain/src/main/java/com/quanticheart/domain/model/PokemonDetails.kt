package com.quanticheart.domain.model

import java.io.Serializable

data class PokemonDetails(
    val id: String,
    val name: String,
    val imageURL: String,
    val description: String,
) : Serializable