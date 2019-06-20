package com.mamontov.presentation

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo

abstract class BasePresenter<T : MvpView> : MvpPresenter<T>() {

    val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.clear()
    }

    protected fun Disposable.disposeOnDestroy(): Disposable =
        this.addTo(compositeDisposable)
}