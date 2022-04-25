package com.kudzai.mutswairo.dogpics.di

import com.kudzai.mutswairo.dogpics.api.RetrofitService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {
    private val baseUrl = "https://dog.ceo/api/"

    @Singleton
    @Provides
    fun getRetrofitService(retrofit: Retrofit) : RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}