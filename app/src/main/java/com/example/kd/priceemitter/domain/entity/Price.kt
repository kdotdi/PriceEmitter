package com.example.kd.priceemitter.domain.entity

import java.math.BigDecimal

data class Price(
    val marketName: String,
    val value: BigDecimal,
    val direction: Direction = Direction.UNKNOWN
)