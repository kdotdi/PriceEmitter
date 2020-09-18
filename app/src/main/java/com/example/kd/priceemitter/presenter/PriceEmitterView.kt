package com.example.kd.priceemitter.presenter

import com.example.kd.priceemitter.base.BaseView
import com.example.kd.priceemitter.domain.entity.Price

interface PriceEmitterView : BaseView {
    fun updatePrice(price: Price)
}