package com.quanticheart.repository.mapper

import com.quanticheart.domain.model.PokemonDetails
import com.quanticheart.repository.model.Card
import com.quanticheart.repository.utils.Mapper

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