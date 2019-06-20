package com.mamontov.cattest.di.data

import com.mamontov.cattest.di.screens.MainModule
import dagger.Module

@Module(
    includes = [
        ApiModule::class,
        RoomModule::class,
        RetrofitModule::class,
        MainModule::class
    ]
)
class DataModule