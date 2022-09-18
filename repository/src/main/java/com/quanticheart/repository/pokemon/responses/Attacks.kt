package com.quanticheart.repository.pokemon.responses

import com.google.gson.annotations.SerializedName

data class Attacks(

    @SerializedName("name") val name: String,
    @SerializedName("cost") val cost: List<String>,
    @SerializedName("convertedEnergyCost") val convertedEnergyCost: Int,
    @SerializedName("damage") val damage: String,
    @SerializedName("text") val text: String
)