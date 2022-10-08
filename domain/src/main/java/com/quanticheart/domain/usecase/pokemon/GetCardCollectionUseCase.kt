package com.quanticheart.domain.usecase.pokemon

import com.quanticheart.domain.repository.CollectionRepository

class GetCardCollectionUseCase(private val repository: CollectionRepository) {
    suspend operator fun invoke() = repository.allCollection()
}