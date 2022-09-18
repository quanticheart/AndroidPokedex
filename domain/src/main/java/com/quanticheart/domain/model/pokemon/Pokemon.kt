package com.quanticheart.domain.model.pokemon

import java.io.Serializable

data class Pokemon(
    val id: String,
    val name: String,
    val imageURL: String
) : Serializable