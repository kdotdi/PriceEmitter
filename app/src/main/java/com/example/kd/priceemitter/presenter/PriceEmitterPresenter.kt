package com.example.kd.priceemitter.presenter

import androidx.annotation.VisibleForTesting
import com.example.kd.priceemitter.datasource.PriceRepository
import com.example.kd.priceemitter.domain.entity.Price
import com.example.kd.priceemitter.util.SchedulerProvider
import timber.log.Timber
import java.math.BigDecimal
import javax.inject.Inject

class PriceEmitterPresenter @Inject constructor(
    private val priceRepository: PriceRepository,
    private val schedulerProvider: SchedulerProvider
) : CommonUseCasePresenter<PriceEmitterView>() {
    private val markets =
        arrayOf(Price.MARKET_NAME_OIL, Price.MARKET_NAME_ZLOTY, Price.MARKET_NAME_GOLD)
    private var marketIndex = 0
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var currentPriceValue = BigDecimal.ZERO

    override fun onFirstBind() {
        super.onFirstBind()

        getPricesAtMarketIndex()
    }

    override fun onViewRestoreState() {
        super.onViewRestoreState()

        getPricesAtMarketIndex()
    }

    private fun switchMarketIndex() {
        marketIndex++
        if (marketIndex == markets.size) marketIndex = 0
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getPricesAtMarketIndex() {
        Timber.i("Subscribing to price repository")
        addSubscription(
            priceRepository.prices(markets[marketIndex])
                .subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    if (currentPriceValue != it.value) {
                        currentPriceValue = it.value
                        present { updatePrice(it) }
                    }
                }, {
                    Timber.e("Failed to update the price: $it")
                })
        )
    }

    fun switchMarket() {
        Timber.i("Clearing subscriptions")
        clearSubscriptions()
        Timber.i("Switching market index")
        switchMarketIndex()
        getPricesAtMarketIndex()
    }
}