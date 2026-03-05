package com.hugopolog.data.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hugopolog.data.api.ApiService
import com.hugopolog.data.mapper.toDto
import com.hugopolog.domain.entities.config.query.OrderType
import com.hugopolog.domain.entities.repository.RepositoryModel

class RepositoryPagingSource(
    private val apiService: ApiService,
    private val query: String,
    private val sort: OrderType
) : PagingSource<Int, RepositoryModel>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, RepositoryModel> {

        val page = params.key ?: 1
        return try {

            val searchQuery = query.ifEmpty {
                "created:>2025-01-01"
            }

            val response = apiService.getRepositoryList(
                query = searchQuery,
                sort = "stars",
                order = if (sort == OrderType.ASC) "asc" else "desc",
                page = page,
                perPage = 20
            )

            if (!response.isSuccessful) {
                return LoadResult.Error(
                    Exception("GitHub API error: ${response.code()}")
                )
            }

            val body = response.body()

            val repos = body?.items?.map { it.toDto() } ?: emptyList()

            LoadResult.Page(
                data = repos,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (repos.isEmpty()) null else page + 1
            )

        } catch (e: Exception) {

            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, RepositoryModel>
    ): Int? {
        return state.anchorPosition
    }
}