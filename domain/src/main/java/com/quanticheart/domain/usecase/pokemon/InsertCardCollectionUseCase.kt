package com.quanticheart.domain.usecase.pokemon

import com.quanticheart.domain.repository.CollectionRepository

class InsertCardCollectionUseCase(private val repository: CollectionRepository) {
    suspend operator fun invoke(cardID: String, name: String) =
        repository.addCollection(cardID, name)
}