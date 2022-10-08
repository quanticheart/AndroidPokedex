package com.quanticheart.repository.pokemon.responses

import com.google.gson.annotations.SerializedName

data class ResponseCard (
    @SerializedName("id") val id : String,
    @SerializedName("name") val name : String,
    @SerializedName("supertype") val supertype : String,
    @SerializedName("subtypes") val subtypes : List<String>,
    @SerializedName("level") val level : Int,
    @SerializedName("hp") val hp : Int,
    @SerializedName("types") val types : List<String>,
    @SerializedName("evolvesFrom") val evolvesFrom : String,
    @SerializedName("abilities") val abilities : List<Abilities>,
    @SerializedName("attacks") val attacks : List<Attacks>,
    @SerializedName("weaknesses") val weaknesses : List<Weaknesses>,
    @SerializedName("retreatCost") val retreatCost : List<String>,
    @SerializedName("convertedRetreatCost") val convertedRetreatCost : Int,
    @SerializedName("number") val number : Int,
    @SerializedName("artist") val artist : String,
    @SerializedName("rarity") val rarity : String,
    @SerializedName("flavorText") val flavorText : String,
    @SerializedName("nationalPokedexNumbers") val nationalPokedexNumbers : List<Int>,
    @SerializedName("legalities") val legalities : Legalities,
    @SerializedName("images") val images : Images
)