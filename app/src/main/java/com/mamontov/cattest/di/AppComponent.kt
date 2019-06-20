package com.mamontov.cattest.di

import android.app.Application
import android.content.Context
import com.mamontov.cattest.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {

        @BindsInstance
        abstract fun app(app: Application): Builder

        @BindsInstance
        abstract fun context(context: Context): Builder
    }
}