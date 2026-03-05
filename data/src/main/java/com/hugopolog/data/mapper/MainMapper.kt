package com.hugopolog.data.mapper

import com.hugopolog.data.entities.OwnerDataModel
import com.hugopolog.data.entities.RepositoryDataModel
import com.hugopolog.domain.entities.repository.OwnerModel
import com.hugopolog.domain.entities.repository.RepositoryModel

fun OwnerDataModel.toDto() = OwnerModel(
    login = login,
    avatar_url = avatar_url
)

fun RepositoryDataModel.toDto() = RepositoryModel(
    id = id,
    name = name,
    full_name = full_name,
    description = description,
    html_url = html_url,
    stargazers_count = stargazers_count,
    forks_count = forks_count,
    open_issues_count = open_issues_count,
    language = language,
    topics = topics,
    created_at = created_at,
    updated_at = updated_at,
    owner = owner.toDto()

)
