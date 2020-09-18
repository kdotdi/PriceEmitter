package com.example.kd.priceemitter.di

import android.content.Context
import com.example.kd.priceemitter.PriceEmitterApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityModule::class,
        PriceRepositoryManagerModule::class,
        SchedulerProviderManagerModule::class,
        AndroidInjectionModule::class
    ]
)
interface ApplicationComponent {

    fun inject(priceEmitterApplication: PriceEmitterApplication)

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}