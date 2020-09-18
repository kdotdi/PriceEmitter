package com.example.kd.priceemitter.domain.entity

import java.math.BigDecimal

data class Price(
    val marketName: String,
    var value: BigDecimal,
    var direction: Direction = Direction.UNKNOWN
) {
    companion object {
        const val MARKET_NAME_OIL = "Oil"
        const val MARKET_NAME_ZLOTY = "Zloty"
        const val MARKET_NAME_GOLD = "Gold"
    }
}