package com.example.kd.priceemitter.di

import com.example.kd.priceemitter.datasource.PriceRepository
import com.example.kd.priceemitter.datasource.PriceRepositoryImpl
import com.example.kd.priceemitter.util.SchedulerProvider
import com.example.kd.priceemitter.util.SchedulerProviderImpl
import dagger.Binds
import dagger.Module

@Module
abstract class SchedulerProviderManagerModule {
    @Binds
    abstract fun bindSchedulerProviderImpl(schedulerProvider: SchedulerProviderImpl): SchedulerProvider
}