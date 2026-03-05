package com.hugopolog.domain.repository

import com.hugopolog.domain.entities.config.error.DataError
import com.hugopolog.domain.entities.config.error.DataResult
import com.hugopolog.domain.entities.config.query.OrderType
import com.hugopolog.domain.entities.repository.RepositoryModel

interface MainRepository {
    suspend fun getRepositoryList(query: String, order: OrderType, page : Int): DataResult<List<RepositoryModel>, DataError>
    suspend fun getRepositoryDetail(owner: String, repository: String): DataResult<RepositoryModel, DataError>
}