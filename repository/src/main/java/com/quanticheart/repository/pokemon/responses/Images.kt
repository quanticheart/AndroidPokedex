package com.quanticheart.repository.pokemon.responses

import com.google.gson.annotations.SerializedName

data class Images(

    @SerializedName("small") val small: String,
    @SerializedName("large") val large: String
)