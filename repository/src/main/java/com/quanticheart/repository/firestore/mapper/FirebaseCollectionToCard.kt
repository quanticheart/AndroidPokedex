package com.quanticheart.repository.firestore.mapper

import com.quanticheart.domain.model.pokemon.Card
import com.quanticheart.core.base.repository.utils.Mapper
import com.quanticheart.repository.firestore.request.RequestInsertCard

//
// Created by Jonn Alves on 08/10/22.
//
class FirebaseCollectionToCard : Mapper<RequestInsertCard?, List<Card>> {
    override fun map(source: RequestInsertCard?): List<Card> {
        return source?.list?.mapIndexed { index, insertCard ->
            val card = Card()
            card.position = index
            card.id = insertCard.cardID
            card.name = insertCard.name
            card
        } ?: run { arrayListOf() }
    }
}