package com.example.kd.priceemitter.datasource

import com.example.kd.priceemitter.domain.entity.Price
import io.reactivex.Flowable
import javax.inject.Inject

class PriceRepositoryImpl @Inject constructor() : PriceRepository {
    override fun prices(marketName: String): Flowable<Price> {
        TODO("Not yet implemented")
    }
}