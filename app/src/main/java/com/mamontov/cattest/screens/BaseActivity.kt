package com.mamontov.cattest.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mamontov.cattest.moxy.MvpAppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity : MvpAppCompatActivity(), HasSupportFragmentInjector {

    protected val disposable = CompositeDisposable()

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        disposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
        supportFragmentInjector
}