package com.hugopolog.demoappopen.ui.feature.main

import com.hugopolog.domain.entities.config.query.OrderType
import com.hugopolog.domain.entities.repository.RepositoryModel

data class MainState(
    val repositories: List<RepositoryModel> = emptyList(),
    val query: String = "",
    val order: OrderType = OrderType.DESC,
    val page: Int = 1,
    val isLoading: Boolean = false,
    val endReached: Boolean = false,
    val error: Boolean = false,
)


interface MainActions {

    data class NavigateToDetail(val repository: RepositoryModel) : MainActions

    data class OnQueryChange(val query: String) : MainActions

    data class OnSortChange(val sort: OrderType) : MainActions

    object LoadNextPage : MainActions
}

