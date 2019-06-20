package com.mamontov.domain.usecases

import com.mamontov.domain.reposirory.ImageRepository
import javax.inject.Inject

interface SaveImageUseCase {

    operator fun invoke(url: String)
}

class SaveImageUseCaseImpl @Inject constructor(
    private val imageRepository: ImageRepository
) : SaveImageUseCase {

    override fun invoke(url: String) {
        imageRepository.saveImage(url)
    }
}