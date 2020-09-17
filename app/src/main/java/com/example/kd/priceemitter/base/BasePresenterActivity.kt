package com.example.kd.priceemitter.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

abstract class BasePresenterActivity<P, V> :
    AppCompatActivity() where P : BasePresenter<V>, V : BaseView {

    lateinit var presenter: P

    private lateinit var presenterFactory: PresenterFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)

        presenterFactory = prepareFactory()

        presenter = ViewModelProvider(this, presenterFactory).get(presenterClass())
        onPresenterPrepared(fromStorage = !presenterFactory.isNewlyCreated)
    }

    override fun onStart() {
        super.onStart()

        if (::presenter.isInitialized) {
            presenter.bind(this as V)
        }
    }

    override fun onStop() {
        if (::presenter.isInitialized) {
            presenter.unbind()
        }

        super.onStop()
    }

    abstract fun onPresenterPrepared(fromStorage: Boolean)

    abstract fun presenterClass(): Class<P>

    abstract fun prepareFactory(): PresenterFactory

    abstract fun injectDependencies()
}