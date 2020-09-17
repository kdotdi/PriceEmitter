package com.example.kd.priceemitter.di

import com.example.kd.priceemitter.view.PriceEmitterActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun bindPriceEmitterActivity(): PriceEmitterActivity
}