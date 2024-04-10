package dev.agalperin.cocktails_main

import dev.agalperin.cocktails_main.models.UiCocktailMain
import dev.agalperin.data.CocktailsRepository
import dev.agalperin.data.RequestResult
import dev.agalperin.data.models.Cocktail
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy


class GetAllCocktailsByQueryUsecaseTest {

    private lateinit var mockRepository: CocktailsRepository
    private lateinit var usecase: GetAllCocktailsByQueryUsecase

    @Before
    fun setUp() {
        mockRepository = mock(CocktailsRepository::class.java)
        usecase = GetAllCocktailsByQueryUsecase(mockRepository)
    }

    @Test
    fun testInvokeWhenRepositoryReturnsValidData() = runTest {
        // Given
        val query = "mockQuery"
        val mockUiCocktails = listOf(UiCocktailMain("Mocktail 1"), UiCocktailMain("Mocktail 2"))
        val mockCocktails = listOf(Cocktail("Mocktail 1"), Cocktail("Mocktail 2"))
        `when`(mockRepository.getAll(query)).thenReturn(flowOf(RequestResult.Success(mockCocktails)))

        // When
        val resultFlow = usecase(query)

        // Then
        resultFlow.collect { result ->
            assertEquals(RequestResult.Success(mockUiCocktails), result)
        }
    }

    @Test
    fun testInvokeWhenRepositoryReturnsError() = runTest {
        // Given
        val query = "mockQuery"
        val mockError = RequestResult.Error(data = null, error = Throwable("Mock Error"))
        `when`(mockRepository.getAll(query)).thenReturn(flowOf(mockError))

        // When
        val resultFlow = usecase(query)

        // Then
        resultFlow.collect { result ->
            assertEquals(mockError, result)
        }
    }
}