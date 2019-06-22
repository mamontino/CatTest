package com.mamontov.domain

import com.mamontov.domain.reposirory.ImageRepository
import com.mamontov.domain.usecases.SaveImageUseCase
import com.mamontov.domain.usecases.SaveImageUseCaseImpl
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SaveImageUseCaseImplTest {

	private val repository: ImageRepository = mock()
	private val useCase: SaveImageUseCase = SaveImageUseCaseImpl(repository)

	@Test
	fun `WHEN save image EXPECT call from repository`() {
		val url = "URL"

		useCase.invoke(url)

		verify(repository).saveImage(url)
		verifyNoMoreInteractions(repository)
	}
}