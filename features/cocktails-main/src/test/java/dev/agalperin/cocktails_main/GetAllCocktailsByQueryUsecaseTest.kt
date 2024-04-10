package dev.agalperin.cocktails_main

import dev.agalperin.cocktails_main.models.UiCocktailMain
import dev.agalperin.data.CocktailsRepository
import dev.agalperin.data.RequestResult
import dev.agalperin.data.models.Cocktail
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GetAllCocktailsByQueryUsecaseTest {

    @MockK
    lateinit var mockRepository: CocktailsRepository
    lateinit var usecase: GetAllCocktailsByQueryUsecase

    @Before
    fun setUp() {
        mockRepository = mockk()
        usecase = GetAllCocktailsByQueryUsecase(mockRepository)
    }

    @Test
    fun testInvokeWhenRepositoryReturnsValidData() = runTest {
        // Given
        val query = "b"
        val mockUiCocktails = listOf(UiCocktailMain("Mocktail 1"), UiCocktailMain("Mocktail 2"))
        val mockCocktails = listOf(Cocktail("Mocktail 1"), Cocktail("Mocktail 2"))
        coEvery { mockRepository.getAll(query = query, mergeStrategy = any()) } returns flowOf(
            RequestResult.Success(mockCocktails)
        )

        val resultFlow = usecase(query)

        // Then
        resultFlow.collect { result ->
            assertEquals(RequestResult.Success(mockUiCocktails), result)
        }
    }

    @Test
    fun testInvokeWhenRepositoryReturnsError() = runTest {
        // Given
        val query = "b"
        val mockError = RequestResult.Error(data = null, error = null)

        coEvery { mockRepository.getAll(query = query, mergeStrategy = any()) } returns flowOf(
            mockError
        )

        // When
        val resultFlow = usecase(query)

        // Then
        resultFlow.collect { result ->
            assertEquals(mockError, result)
        }
    }
}