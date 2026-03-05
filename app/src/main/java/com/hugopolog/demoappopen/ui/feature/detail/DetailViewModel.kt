package com.hugopolog.demoappopen.ui.feature.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.hugopolog.demoappopen.ui.feature.BaseViewModel
import com.hugopolog.domain.entities.config.error.DataResult
import com.hugopolog.domain.usecase.GetRepositoryDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getRepositoryDetailUseCase: GetRepositoryDetailUseCase
) : BaseViewModel() {

    var screenState by mutableStateOf(DetailState())
        private set

    fun onAction(action: DetailActions) {
        when (action) {
            is DetailActions.NavigateBack -> {
                navigateUp()
            }

            is DetailActions.RetryLoad -> {
                loadRepository()
            }

            is DetailActions.LoadRepository -> {
                screenState = screenState.copy(
                    ownerId = action.owner,
                    repositoryId = action.repository
                )
                loadRepository()
            }
        }
    }

    private fun loadRepository() {
        screenState = screenState.copy(isLoading = true)

        viewModelScope.launch {

            when (val result = getRepositoryDetailUseCase(screenState.ownerId, screenState.repositoryId)) {

                is DataResult.Success -> {
                    screenState = screenState.copy(
                        repository = result.data,
                        isLoading = false
                    )
                }

                is DataResult.Error -> {
                    screenState = screenState.copy(
                        isLoading = false,
                        error = result.error.toString()
                    )
                }
            }
        }
    }
}

