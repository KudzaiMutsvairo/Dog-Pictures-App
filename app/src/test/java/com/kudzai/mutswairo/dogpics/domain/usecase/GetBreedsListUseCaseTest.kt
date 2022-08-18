package com.kudzai.mutswairo.dogpics.domain.usecase

import com.kudzai.mutswairo.dogpics.data.RepositoryFake
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetBreedsListUseCaseTest {
    private lateinit var repository: RepositoryFake
    private lateinit var getBreedsListUseCase: GetBreedsListUseCase

    @Before
    fun setUp() {
        repository = RepositoryFake()
        getBreedsListUseCase = GetBreedsListUseCase(repository)
    }

    @Test
    fun `GIVEN valid breed WHEN retrieving a single breed THEN returns true`() = runTest {
        // Assign
        val breed = "bulldog"
        // Act and Assert
        getBreedsListUseCase.invoke().collect {
            assert(it.isNotEmpty())
            assert(it.contains(breed))
        }
    }
}
