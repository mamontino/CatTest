package com.mamontov.domain

import com.mamontov.domain.entities.Cat
import com.mamontov.domain.reposirory.CatsRepository
import com.mamontov.domain.usecases.RemoveFromFavouritesUseCase
import com.mamontov.domain.usecases.RemoveFromFavouritesUseCaseImpl
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoveFromFavouritesUseCaseImplTest {

    private val repository: CatsRepository = mock()
    private val useCase: RemoveFromFavouritesUseCase = RemoveFromFavouritesUseCaseImpl(repository)

    @Test
    fun `WHEN remove from favorites EXPECT complete`() {
        val cat = Cat("id", "url")

        whenever(repository.removeFromFavorites(cat)).thenReturn(Completable.complete())

        useCase.invoke(cat)

        verify(repository).removeFromFavorites(cat)
        verifyNoMoreInteractions(repository)
    }
}