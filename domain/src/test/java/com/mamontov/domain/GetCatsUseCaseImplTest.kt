package com.mamontov.domain

import com.mamontov.domain.entities.Cat
import com.mamontov.domain.reposirory.CatsRepository
import com.mamontov.domain.usecases.GetCatsUseCase
import com.mamontov.domain.usecases.GetCatsUseCaseImpl
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.rxkotlin.toSingle
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCatsUseCaseImplTest {

	private val repository: CatsRepository = mock()
	private val useCase: GetCatsUseCase = GetCatsUseCaseImpl(repository)

	@Test
	fun `WHEN get cats EXPECT call from repository`() {
		val page = 10

		whenever(repository.getCats(page)).thenReturn(emptyList<Cat>().toSingle())

		useCase.invoke(page)

		verify(repository).getCats(page)
		verifyNoMoreInteractions(repository)
	}
}