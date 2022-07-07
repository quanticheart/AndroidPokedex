package com.quanticheart.domain.model

import java.io.Serializable

data class Pokemon(
    val id: String,
    val name: String,
    val imageURL: String
) : Serializable