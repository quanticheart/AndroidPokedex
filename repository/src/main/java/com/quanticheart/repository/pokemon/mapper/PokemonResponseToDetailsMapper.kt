package com.quanticheart.repository.pokemon.mapper

import com.quanticheart.domain.model.pokemon.PokemonDetails
import com.quanticheart.repository.pokemon.responses.ResponseCard
import com.quanticheart.core.base.repository.utils.Mapper

class PokemonResponseToDetailsMapper : Mapper<ResponseCard, PokemonDetails> {
    override fun map(source: ResponseCard): PokemonDetails {
        return PokemonDetails(
            name = source.name,
            imageURL = source.images.large,
            id = source.id,
            description = source.flavorText
        )
    }
}