package com.mamontov.domain

import com.mamontov.domain.entities.Cat
import com.mamontov.domain.reposirory.CatsRepository
import com.mamontov.domain.usecases.GetFavouritesUseCase
import com.mamontov.domain.usecases.GetFavouritesUseCaseImpl
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.rxkotlin.toSingle
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetFavouritesUseCaseImplTest {

	private val repository: CatsRepository = mock()
	private val useCase: GetFavouritesUseCase = GetFavouritesUseCaseImpl(repository)

	@Test
	fun `WHEN get favorites EXPECT EXPECT call from repository`() {
		whenever(repository.getFavorites()).thenReturn(emptyList<Cat>().toSingle())

		useCase.invoke()

		verify(repository).getFavorites()
		verifyNoMoreInteractions(repository)
	}
}