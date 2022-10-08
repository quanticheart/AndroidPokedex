package com.quanticheart.domain.usecase.pokemon

import com.quanticheart.domain.repository.CollectionRepository

class DeleteCardCollectionUseCase(private val repository: CollectionRepository) {
    suspend operator fun invoke(cardID: String, name: String) =
        repository.deleteCollectionBy(cardID, name)
}