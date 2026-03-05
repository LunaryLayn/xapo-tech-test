package com.hugopolog.domain.usecase

import com.hugopolog.domain.repository.MainRepository
import javax.inject.Inject

class GetRepositoryDetailUseCase @Inject constructor(val repository: MainRepository) {
    suspend operator fun invoke(owner: String, repositoryId: String) = repository.getRepositoryDetail(owner, repositoryId)
}
