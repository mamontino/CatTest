package com.mamontov.cattest.di.screens

import com.mamontov.domain.usecases.*
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface FavoritesFragmentModule{

    @Binds
    fun bindGetFavoritesUseCase(useCase: GetFavoritesUseCaseImpl): GetFavoritesUseCase

    @Binds
    @Reusable
    fun bindSaveImageUseCase(useCase: SaveImageUseCaseImpl): SaveImageUseCase

    @Binds
    @Reusable
    fun bindRemoveFromFavoritesUseCase(useCase: RemoveFromFavouritesUseCaseImpl): RemoveFromFavouritesUseCase

    @Binds
    @Reusable
    fun bindAddFavoritesUseCase(useCase: AddToFavouritesUseCaseImpl): AddToFavouritesUseCase
}
