package com.quanticheart.repository.pokemon.mapper

import com.quanticheart.domain.model.pokemon.PokemonDetails
import com.quanticheart.repository.pokemon.responses.Card
import com.quanticheart.repository.base.utils.Mapper

class PokemonResponseToDetailsMapper : Mapper<Card, PokemonDetails> {
    override fun map(source: Card): PokemonDetails {
        return PokemonDetails(
            name = source.name,
            imageURL = source.images.large,
            id = source.id,
            description = source.flavorText
        )
    }
}