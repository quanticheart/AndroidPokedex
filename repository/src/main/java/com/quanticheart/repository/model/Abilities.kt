package com.quanticheart.repository.model

import com.google.gson.annotations.SerializedName

data class Abilities(
    @SerializedName("name") val name: String,
    @SerializedName("text") val text: String,
    @SerializedName("type") val type: String
)