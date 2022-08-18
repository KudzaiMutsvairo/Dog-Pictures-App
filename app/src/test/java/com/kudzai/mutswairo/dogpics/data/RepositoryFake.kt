package com.kudzai.mutswairo.dogpics.data

import com.kudzai.mutswairo.dogpics.data.remote.Resource
import com.kudzai.mutswairo.dogpics.domain.repositories.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RepositoryFake : Repository {
    private val dogsList = listOf<String>(
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_1003.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_1007.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_1023.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_10263.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_10715.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_10822.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_10832.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_10982.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_11006.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_11172.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_11182.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_1126.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_1128.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_11432.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_1145.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_115.jpg",
        "https://images.dog.ceo/breeds/hound-afghan/n02088094_1150.jpg"
    )

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

    override suspend fun getDogsByBreed(breed: String): Flow<Resource<List<String>>> {
        return if (getAppDogBreeds().contains(breed)) {
            flowOf(Resource.Success(dogsList))
        } else {
            flowOf(Resource.Error("Dog Breed Not in Database"))
        }
    }

}