package com.hugopolog.domain.usecase

import com.hugopolog.domain.entities.config.query.OrderType
import com.hugopolog.domain.repository.MainRepository
import javax.inject.Inject

class GetRepositoryListUseCase @Inject constructor(
    private val repository: MainRepository
) {

    suspend operator fun invoke(
        query: String,
        order: OrderType,
        page: Int
    ) = repository.getRepositoryList(
        query = buildQuery(query),
        order = order,
        page = page
    )

    private fun buildQuery(query: String): String {
        return query.ifBlank {
            "created:>=2025-01-01"
        }
    }
}