package com.quanticheart.domain.model.pokemon

import java.io.Serializable

data class PokemonDetails(
    val id: String,
    val name: String,
    val imageURL: String,
    val description: String,
    val number: String,
    val artist: String,
    val rarity: String,
    val type: String,
) : Serializable