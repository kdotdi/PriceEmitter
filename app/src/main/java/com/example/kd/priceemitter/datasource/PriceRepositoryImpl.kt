package com.example.kd.priceemitter.datasource

import com.example.kd.priceemitter.domain.entity.Direction
import com.example.kd.priceemitter.domain.entity.Price
import io.reactivex.Emitter
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import java.math.BigDecimal
import java.util.concurrent.Callable
import javax.inject.Inject
import kotlin.random.Random

class PriceRepositoryImpl @Inject constructor() : PriceRepository {
    override fun prices(marketName: String): Flowable<Price> =
        Flowable.generate(
            Callable { Price(marketName, BigDecimal.ZERO, Direction.UNKNOWN) },
            BiFunction { price: Price, emitter: Emitter<Price> ->
                val previousPrice = price.value
                val nextPrice = generateRandom()
                when (previousPrice.compareTo(nextPrice)) {
                    -1 -> price.direction = Direction.UP
                    0 -> price.direction = Direction.UNKNOWN
                    1 -> price.direction = Direction.DOWN
                }
                price.value = nextPrice
                emitter.onNext(price)
                price
            })

    private fun generateRandom(): BigDecimal {
        val multiplyFactor = 100
        val scale = 2
        val roundingMode = BigDecimal.ROUND_UP
        return (Random.nextDouble() * multiplyFactor).toBigDecimal().setScale(scale, roundingMode)
    }
}