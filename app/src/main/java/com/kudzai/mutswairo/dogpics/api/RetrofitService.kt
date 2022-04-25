package com.kudzai.mutswairo.dogpics.api

import com.kudzai.mutswairo.dogpics.api.DogsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("/{breed}")
    suspend fun getDogs(@Path("breed") breed: String) : Response<DogsResponse>
}