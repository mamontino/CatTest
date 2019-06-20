package com.mamontov.cattest.di

import android.content.Context
import com.mamontov.cattest.R
import com.mamontov.cattest.di.data.DataModule
import com.mamontov.cattest.di.screens.MainActivityModule
import com.mamontov.cattest.screens.main.MainActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        DataModule::class
    ]
)
abstract class AppModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @AppScope
        fun provideAppName(context: Context): String = context.getString(R.string.app_name)
    }

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun provideMainActivity(): MainActivity

}