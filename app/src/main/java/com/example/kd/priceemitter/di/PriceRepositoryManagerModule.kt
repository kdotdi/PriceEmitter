package com.example.kd.priceemitter.di

import com.example.kd.priceemitter.datasource.PriceRepository
import com.example.kd.priceemitter.datasource.PriceRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class PriceRepositoryManagerModule {
    @Binds
    abstract fun bindPriceRepositoryImpl(priceRepositoryImpl: PriceRepositoryImpl): PriceRepository
}