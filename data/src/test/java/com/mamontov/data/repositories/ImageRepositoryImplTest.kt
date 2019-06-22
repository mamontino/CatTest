package com.mamontov.data.repositories

import com.mamontov.data.datasources.ImageDataSource
import com.mamontov.data.reposirory.ImageRepositoryImpl
import com.mamontov.domain.reposirory.ImageRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImageRepositoryImplTest {

	private val dataSource: ImageDataSource = mock()
	private val repository: ImageRepository = ImageRepositoryImpl(dataSource)

	@Test
	fun `WHEN save image EXPECT call from dataSource`() {
		val url = "URL"

		repository.saveImage(url)

		verify(dataSource).saveImage(url)
		verifyNoMoreInteractions(dataSource)
	}
}