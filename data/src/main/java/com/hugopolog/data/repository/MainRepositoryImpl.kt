package com.hugopolog.data.repository

import com.hugopolog.data.api.ApiService
import com.hugopolog.data.mapper.toDto
import com.hugopolog.data.util.ApiHelper.safeApiCall
import com.hugopolog.domain.entities.config.error.DataError
import com.hugopolog.domain.entities.config.error.DataResult
import com.hugopolog.domain.entities.config.query.OrderType
import com.hugopolog.domain.entities.repository.RepositoryModel
import com.hugopolog.domain.repository.MainRepository

class MainRepositoryImpl(
    private val service: ApiService
) : MainRepository {
    override suspend fun getRepositoryList(query: String, order: OrderType, page : Int): DataResult<List<RepositoryModel>, DataError> {
        return safeApiCall(
            apiCall = { service.getRepositoryList(query = query, order = order.name, page = page, perPage = 10) },
            mapper = { items ->
                items.items.map { it.toDto() }
            }
        )
    }


    override suspend fun getRepositoryDetail(owner: String, repository: String): DataResult<RepositoryModel, DataError> {
        return safeApiCall(
            apiCall = { service.getRepositoryDetail(owner, repository) },
            mapper = { repo ->
                repo.toDto()
            }
        )
    }
}