package com.mamontov.cattest.di.screens

import com.mamontov.domain.usecases.*
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface CatsFragmentModule {

    @Binds
    fun bindGetCatsUseCase(useCase: GetCatsUseCaseImpl): GetCatsUseCase

    @Binds
    @Reusable
    fun bindSaveImageUseCase(useCase: SaveImageUseCaseImpl): SaveImageUseCase

    @Binds
    @Reusable
    fun bindRemoveFromFavoritesUseCase(useCase: RemoveFromFavoritesUseCaseImpl): RemoveFromFavoritesUseCase

    @Binds
    @Reusable
    fun bindAddToFavoritesUseCase(useCase: AddToFavoritesUseCaseImpl): AddToFavoritesUseCase
}
