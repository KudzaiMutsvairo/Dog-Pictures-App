package com.kudzai.mutswairo.dogpics.data.remote

import com.kudzai.mutswairo.dogpics.domain.repositories.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val apiService: RetrofitService) : Repository {

    override fun getAppDogBreeds(): List<String> {
        return listOf(
            "african",
            "bulldog",
            "bullterrier",
            "chihuahua",
            "greyhound",
            "dalmatian",
            "doberman",
            "germanshepherd",
            "hound",
            "husky",
            "malamute",
            "mastiff",
            "pitbull",
            "pomeranian",
            "pug",
            "ridgeback",
            "rottweiler",
            "terrier",
            "wolfhound",
            "springer",
            "shiba",
            "poodle"
        )
    }

    override suspend fun getDogsByBreed(breed: String): Flow<Resource<List<String>>> = flow {
        emit(Resource.Loading(null, "Loading Images"))
        val response = apiService.getDogs(breed)
        if (response.isSuccessful) {
            if (response.body()!!.status == "success") {
                emit(Resource.Success(response.body()!!.message))
            } else {
                emit(Resource.Error("Error: Could not Fetch Dogs"))
            }
        } else {
            emit(Resource.Error("Error: Could not Fetch Dogs"))
        }
    }
}