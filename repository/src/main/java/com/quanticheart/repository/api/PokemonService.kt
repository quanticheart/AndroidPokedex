package com.quanticheart.repository.api

import com.quanticheart.repository.model.Card
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("tcg/classic")
    suspend fun getPokemons(): List<Card>

    @GET("tcg/classic/{id}")
    suspend fun getPokemon(
        @Path("id") id: String
    ): Card
}