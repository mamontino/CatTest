package com.mamontov.domain

import com.mamontov.domain.reposirory.CatsRepository
import com.mamontov.domain.usecases.AddToFavouritesUseCase
import com.mamontov.domain.usecases.AddToFavouritesUseCaseImpl
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AddToFavouritesUseCaseImplTest {

	private val repository: CatsRepository = mock()
	private val useCase: AddToFavouritesUseCase = AddToFavouritesUseCaseImpl(repository)

	@Test
	fun `WHEN add to favorites EXPECT complete`() {
		val id = "id"
		val url = "url"

		whenever(repository.addToFavorites(id, url)).thenReturn(Completable.complete())

		useCase.invoke(id, url)

		verify(repository).addToFavorites(id, url)
		verifyNoMoreInteractions(repository)
	}
}