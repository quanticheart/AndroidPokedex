package com.quanticheart.repository.pokemon.responses

import com.google.gson.annotations.SerializedName

data class Legalities(

    @SerializedName("unlimited") val unlimited: String
)