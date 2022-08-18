package com.kudzai.mutswairo.dogpics.domain.repositories

import com.kudzai.mutswairo.dogpics.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getAppDogBreeds(): List<String>
    suspend fun getDogsByBreed(breed: String): Flow<Resource<List<String>>>
}
