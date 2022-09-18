package com.quanticheart.repository.pokemon.mapper

import com.quanticheart.domain.model.Pokemon
import com.quanticheart.repository.pokemon.responses.Card
import com.quanticheart.repository.base.utils.Mapper

class PokemonResponseToListMapper : Mapper<List<Card>, List<Pokemon>> {
    override fun map(source: List<Card>): List<Pokemon> =
        source.map {
            Pokemon(
                id = it.id,
                name = it.name,
                imageURL = it.images.small
            )
        }

}