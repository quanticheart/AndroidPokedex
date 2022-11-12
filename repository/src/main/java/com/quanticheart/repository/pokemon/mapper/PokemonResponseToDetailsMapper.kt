package com.quanticheart.repository.pokemon.mapper

import com.quanticheart.core.base.repository.utils.Mapper
import com.quanticheart.domain.model.pokemon.PokemonDetails
import com.quanticheart.repository.pokemon.responses.ResponseCard

class PokemonResponseToDetailsMapper : Mapper<ResponseCard, PokemonDetails> {
    override fun map(source: ResponseCard): PokemonDetails {
        return PokemonDetails(
            name = source.name,
            imageURL = source.images.large,
            id = source.id,
            description = validate(source.flavorText),
            number = "${source.number}º",
            artist = validate(source.artist),
            rarity = validate(source.rarity),
            type = validate(source.supertype),
        )
    }

    private fun validate(data: String?) = if (data.isNullOrEmpty()) "--" else data
}