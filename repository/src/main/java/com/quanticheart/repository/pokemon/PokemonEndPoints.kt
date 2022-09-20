package com.quanticheart.repository.pokemon

import com.quanticheart.repository.pokemon.responses.Card
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonEndPoints {
    @GET("classic")
    suspend fun getPokemons(): Response<List<Card>>

    @GET("classic/{id}")
    suspend fun getPokemon(@Path("id") id: String): Response<Card>
}