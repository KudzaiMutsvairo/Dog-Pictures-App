package com.kudzai.mutswairo.dogpics.data.remote

import com.kudzai.mutswairo.dogpics.data.remote.dto.GetDogsByBreedDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("/{breed}")
    suspend fun getDogs(@Path("breed") breed: String): Response<GetDogsByBreedDTO>
}
