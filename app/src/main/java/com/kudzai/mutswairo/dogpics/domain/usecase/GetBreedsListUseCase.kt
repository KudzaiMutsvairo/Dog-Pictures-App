package com.kudzai.mutswairo.dogpics.domain.usecase

import com.kudzai.mutswairo.dogpics.domain.repositories.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetBreedsListUseCase @Inject constructor(
    private val repository: Repository
) : UseCase<List<String>, Unit?>() {
    override suspend operator fun invoke(params: Unit?): Flow<List<String>> {
        return flowOf(repository.getAppDogBreeds())
    }
}
