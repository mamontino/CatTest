package com.mamontov.cattest

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.multidex.MultiDexApplication
import com.arellomobile.mvp.RegisterMoxyReflectorPackages
import com.facebook.stetho.Stetho
import com.mamontov.cattest.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

@RegisterMoxyReflectorPackages("com.mamontov.cattest.presentation")
class App : MultiDexApplication(), HasActivityInjector , HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

       DaggerAppComponent
            .builder()
            .app(this)
            .context(this)
            .create(this)
            .inject(this)

        Stetho.initializeWithDefaults(this)

        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

//        RxJavaPlugins.setErrorHandler(RxErrorHandler(errorAnalytics))
    }

    override fun activityInjector(): AndroidInjector<Activity> =
        AndroidInjector { activity ->
            dispatchingAndroidInjector.maybeInject(activity)
        }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
        AndroidInjector {
            supportFragmentInjector().inject(it)
        }
}
