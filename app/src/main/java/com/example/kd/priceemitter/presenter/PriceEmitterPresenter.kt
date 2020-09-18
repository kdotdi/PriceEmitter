package com.example.kd.priceemitter.presenter

import com.example.kd.priceemitter.datasource.PriceRepository
import com.example.kd.priceemitter.domain.entity.Price
import javax.inject.Inject

class PriceEmitterPresenter @Inject constructor(
    private val priceRepository: PriceRepository
) : CommonUseCasePresenter<PriceEmitterView>() {
    private val markets = arrayOf(Price.MARKET_NAME_OIL, Price.MARKET_NAME_ZLOTY, Price.MARKET_NAME_GOLD)
    private var marketIndex = 0

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

    private fun getPricesAtMarketIndex() {
        addSubscription(priceRepository.prices(markets[marketIndex])
            .subscribeOn(computationScheduler)
            .observeOn(uiThread)
            .subscribe {
                present { updatePrice(it) }
            })
    }

    fun switchMarket() {
        clearSubscriptions()
        switchMarketIndex()
        getPricesAtMarketIndex()
    }
}