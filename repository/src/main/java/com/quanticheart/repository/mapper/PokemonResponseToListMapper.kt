package com.quanticheart.repository.mapper

import com.quanticheart.domain.model.Pokemon
import com.quanticheart.repository.model.Card
import com.quanticheart.repository.utils.Mapper

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