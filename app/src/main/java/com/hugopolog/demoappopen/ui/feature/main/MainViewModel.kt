package com.hugopolog.demoappopen.ui.feature.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.hugopolog.demoappopen.navigation.AppScreens
import com.hugopolog.demoappopen.ui.feature.BaseViewModel
import com.hugopolog.domain.entities.config.error.DataResult
import com.hugopolog.domain.usecase.GetRepositoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRepositoryListUseCase: GetRepositoryListUseCase
) : BaseViewModel() {

    var screenState by mutableStateOf(MainState())
        private set

    private val queryFlow = MutableStateFlow("")

    init {

        viewModelScope.launch {
            queryFlow
                .debounce(500)
                .distinctUntilChanged()
                .collect { query ->
                    screenState = screenState.copy(query = query)
                    loadRepositories()
                }
        }

        loadRepositories()
    }

    fun onAction(action: MainActions) {
        when (action) {

            is MainActions.OnQueryChange -> {
                queryFlow.value = action.query
            }

            is MainActions.OnSortChange -> {
                screenState = screenState.copy(order = action.sort)
                loadRepositories()
            }

            MainActions.LoadNextPage -> paginatedLoad()

            is MainActions.NavigateToDetail -> {
                navigate(
                    AppScreens.DetailScreen(
                        action.repository.owner.login,
                        action.repository.name
                    )
                )
            }
        }
    }

    private fun clearCurrentSearch() {
        screenState = screenState.copy(
            repositories = emptyList(),
            page = 1,
            endReached = false,
            error = false
        )
    }

    private fun loadRepositories() {
        clearCurrentSearch()

        viewModelScope.launch {

            screenState = screenState.copy(isLoading = true)

            when (val result = getRepositoryListUseCase(
                screenState.query,
                screenState.order,
                screenState.page
            )) {

                is DataResult.Error -> {
                    screenState = screenState.copy(
                        isLoading = false,
                        error = true
                    )
                }

                is DataResult.Success -> {

                    val newRepos = result.data

                    screenState = screenState.copy(
                        repositories = newRepos,
                        isLoading = false,
                        endReached = newRepos.isEmpty()
                    )
                }
            }
        }
    }

    private fun paginatedLoad() {

        if (screenState.isLoading || screenState.endReached) return

        viewModelScope.launch {

            screenState = screenState.copy(isLoading = true)

            val nextPage = screenState.page + 1

            when (val result = getRepositoryListUseCase(
                screenState.query,
                screenState.order,
                nextPage
            )) {

                is DataResult.Error -> {
                    screenState = screenState.copy(
                        isLoading = false,
                        error = true
                    )
                }

                is DataResult.Success -> {

                    val newRepos = result.data

                    screenState = screenState.copy(
                        repositories = screenState.repositories + newRepos,
                        page = nextPage,
                        isLoading = false,
                        endReached = newRepos.isEmpty()
                    )
                }
            }
        }
    }
}