package com.hugopolog.data.api

import com.hugopolog.data.entities.RepositoryDataModel
import com.hugopolog.data.entities.RepositoryResponseDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("search/repositories")
    suspend fun getRepositoryList(
        @Query("q") query: String = "created:>2025-01-01",
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc",
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<RepositoryResponseDataModel>

        @GET("repos/{owner}/{repo}")
        suspend fun getRepositoryDetail(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<RepositoryDataModel>
}