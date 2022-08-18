package com.kudzai.mutswairo.dogpics.domain.usecase

import com.kudzai.mutswairo.dogpics.data.remote.Resource
import com.kudzai.mutswairo.dogpics.domain.repositories.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetDogsByBreedUseCase @Inject constructor(private val repository: Repository) : UseCase<Resource<List<String>>, String?>() {
    override suspend operator fun invoke(breed: String?): Flow<Resource<List<String>>> {
        return breed?.let {
            repository.getDogsByBreed(breed)
        } ?: flowOf(Resource.Error("Breed parameter cannot be empty"))
    }
}
