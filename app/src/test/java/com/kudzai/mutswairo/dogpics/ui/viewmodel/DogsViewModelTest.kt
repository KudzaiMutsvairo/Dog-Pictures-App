package com.kudzai.mutswairo.dogpics.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kudzai.mutswairo.dogpics.data.RepositoryFake
import com.kudzai.mutswairo.dogpics.data.remote.Status
import com.kudzai.mutswairo.dogpics.domain.usecase.GetBreedsListUseCase
import com.kudzai.mutswairo.dogpics.domain.usecase.GetDogsByBreedUseCase
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DogsViewModelTest {
    private lateinit var repository: RepositoryFake
    private lateinit var getDogsByBreedUseCase: GetDogsByBreedUseCase
    private lateinit var getBreedsListUseCase: GetBreedsListUseCase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        repository = RepositoryFake()
        getDogsByBreedUseCase = GetDogsByBreedUseCase(repository)
        getBreedsListUseCase = GetBreedsListUseCase(repository)
    }

    @Test
    fun `get dog breeds`() = runTest {
        // Assign
        val viewModel = DogsViewModel(getDogsByBreedUseCase, getBreedsListUseCase)

        // Act
        viewModel.getDogBreeds()
        val liveData = viewModel.dogBreeds
        liveData.observeForever { }
        runCurrent()

        // Assert
        assert(liveData.value!!.contains("african"))
    }

    @Test
    fun `GIVEN empty parameter WHEN fetching Dogs THEN return Error Status`() = runTest {
        // Assign
        val viewModel = DogsViewModel(getDogsByBreedUseCase, getBreedsListUseCase)

        // Act
        viewModel.getDogPics("")
        val liveData = viewModel.dogPics
        liveData.observeForever { }
        runCurrent()

        // Assert
        assert(liveData.value!!.status == Status.ERROR)
    }

    @Test
    fun `GIVEN valid parameter WHEN fetching Dogs THEN return Success Status`() = runTest {
        // Assign
        val viewModel = DogsViewModel(getDogsByBreedUseCase, getBreedsListUseCase)

        // Act
        viewModel.getDogPics("african")
        val liveData = viewModel.dogPics
        liveData.observeForever { }
        runCurrent()

        // Assert
        assert(liveData.value!!.status == Status.SUCCESS)
    }
}
