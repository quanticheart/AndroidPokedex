package com.quanticheart.domain.usecase.remote

import com.quanticheart.domain.repository.AppRepository

class GetTcgRemoteUseCase(private val repository: AppRepository) {
    suspend operator fun invoke() = repository.getTcg()
}
