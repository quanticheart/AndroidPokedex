package com.quanticheart.repository.pokemon.mapper

import com.quanticheart.domain.model.pokemon.Pokemon
import com.quanticheart.repository.pokemon.responses.ResponseCard
import com.quanticheart.core.base.repository.utils.Mapper

class PokemonResponseToListMapper : Mapper<List<ResponseCard>, List<Pokemon>> {
    override fun map(source: List<ResponseCard>): List<Pokemon> =
        source.map {
            Pokemon(
                id = it.id,
                name = it.name,
                imageURL = it.images.small
            )
        }

}