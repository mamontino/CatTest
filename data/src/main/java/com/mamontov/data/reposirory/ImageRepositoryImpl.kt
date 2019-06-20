package com.mamontov.data.reposirory

import com.mamontov.data.datasources.ImageDataSource
import com.mamontov.domain.reposirory.ImageRepository
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(private val imageDataSource: ImageDataSource) : ImageRepository {

    override fun saveImage(url: String) {
        return imageDataSource.saveImage(url)
    }
}