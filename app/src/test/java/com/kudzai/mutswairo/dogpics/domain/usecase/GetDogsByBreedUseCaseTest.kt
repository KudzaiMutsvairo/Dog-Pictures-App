package com.kudzai.mutswairo.dogpics.domain.usecase

import com.kudzai.mutswairo.dogpics.data.RepositoryFake
import com.kudzai.mutswairo.dogpics.data.remote.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetDogsByBreedUseCaseTest {
    private lateinit var repository: RepositoryFake
    private lateinit var getDogsByBreedUseCase: GetDogsByBreedUseCase

    @Before
    fun setUp() {
        repository = RepositoryFake()
        getDogsByBreedUseCase = GetDogsByBreedUseCase(repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `GIVEN valid breed WHEN retrieving dogs THEN return dogs`() = runTest {
        // Assign
        val breed = "greyhound"
        // Act and Assert
        getDogsByBreedUseCase.invoke(breed).collect {
            assert(it.status == Status.SUCCESS)
            assert(it.data!!.isNotEmpty())
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `GIVEN invalid breed WHEN retrieving dogs THEN return error`() = runTest {
        // Assign
        val breed = "xyz"
        // Act and Assert
        getDogsByBreedUseCase.invoke(breed).collect {
            assert(it.status == Status.ERROR)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `GIVEN null parameter WHEN retrieving dogs THEN return error`() = runTest {
        getDogsByBreedUseCase.invoke(null).collect {
            assert(it.status == Status.ERROR)
        }
    }
}
