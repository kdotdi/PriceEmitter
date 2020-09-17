package com.example.kd.priceemitter.datasource

import com.example.kd.priceemitter.domain.entity.Price
import io.reactivex.Flowable

interface PriceRepository {
    fun prices(marketName: String): Flowable<Price>
}