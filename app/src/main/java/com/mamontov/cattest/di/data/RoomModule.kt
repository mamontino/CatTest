package com.mamontov.cattest.di.data

import android.app.Application
import androidx.room.Room
import com.mamontov.cattest.BuildConfig
import com.mamontov.cattest.di.AppScope
import com.mamontov.data.database.CatDao
import com.mamontov.data.database.CatDatabase
import dagger.Module
import dagger.Provides

@Module
class RoomModule {

    @Module
    companion object {

        @JvmStatic
        @AppScope
        @Provides
        fun provideDb(context: Application): CatDatabase =
            Room.databaseBuilder<CatDatabase>(
                context.applicationContext,
                CatDatabase::class.java,
                BuildConfig.DATABASE_NAME
            )
                .build()

        @JvmStatic
        @AppScope
        @Provides
        fun provideUserDao(db: CatDatabase): CatDao =
            db.catDao()
    }
}