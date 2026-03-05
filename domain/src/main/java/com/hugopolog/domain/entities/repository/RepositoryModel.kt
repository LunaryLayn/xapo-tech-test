package com.hugopolog.domain.entities.repository

data class RepositoryModel(
    val id: Long,
    val name: String,
    val full_name: String,
    val description: String?,
    val html_url: String,

    val stargazers_count: Int,
    val forks_count: Int,
    val open_issues_count: Int,

    val language: String?,
    val topics: List<String>?,

    val created_at: String,
    val updated_at: String,

    val owner: OwnerModel
)
