package com.example.kd.priceemitter.view

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.kd.priceemitter.R
import com.example.kd.priceemitter.base.BasePresenterActivity
import com.example.kd.priceemitter.base.PresenterFactory
import com.example.kd.priceemitter.presenter.PriceEmitterPresenter
import com.example.kd.priceemitter.presenter.PriceEmitterView
import dagger.android.AndroidInjection
import javax.inject.Inject
import javax.inject.Provider

class PriceEmitterActivity :
    BasePresenterActivity<PriceEmitterPresenter, PriceEmitterView>(),
    PriceEmitterView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @Inject
    lateinit var priceEmitterPresenter: Provider<PriceEmitterPresenter>

    override fun injectDependencies() = AndroidInjection.inject(this)

    override fun onPresenterPrepared(fromStorage: Boolean) = Unit

    override fun presenterClass(): Class<PriceEmitterPresenter> = PriceEmitterPresenter::class.java

    override fun prepareFactory(): PresenterFactory {
        return object : PresenterFactory() {
            override fun <T : ViewModel> createPresenter(presenterClass: Class<T>): T {
                return priceEmitterPresenter.get() as T
            }
        }
    }
}
