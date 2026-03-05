package com.hugopolog.demoappopen.ui.feature.detail

import com.hugopolog.domain.entities.repository.RepositoryModel

data class DetailState(
    val repository : RepositoryModel? = null,
    val isLoading : Boolean = false,
    val error : String? = null,
    val ownerId: String = "",
    val repositoryId: String = ""
)


interface DetailActions {
    data object NavigateBack  : DetailActions
    data object RetryLoad  : DetailActions
    data class LoadRepository ( val owner: String, val repository: String)  : DetailActions
}

