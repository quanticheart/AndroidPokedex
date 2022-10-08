package com.quanticheart.domain.usecase.pokemon

import com.quanticheart.domain.repository.CollectionRepository

class GetCardUseCase(private val repository: CollectionRepository) {
    suspend operator fun invoke(cardID: String) = repository.findCollectionBy(cardID)
}