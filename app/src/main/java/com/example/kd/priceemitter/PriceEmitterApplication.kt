package com.example.kd.priceemitter

import android.app.Application
import com.example.kd.priceemitter.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class PriceEmitterApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder()
            .context(this)
            .build()
            .inject(this)

        if (BuildConfig.DEBUG) {

        }
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
