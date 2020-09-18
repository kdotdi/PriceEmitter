package com.example.kd.priceemitter.presenter

import com.example.kd.priceemitter.datasource.PriceRepository
import com.example.kd.priceemitter.domain.entity.Direction
import com.example.kd.priceemitter.domain.entity.Price
import com.example.kd.priceemitter.util.TestSchedulerProviderImpl
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import timber.log.Timber
import java.math.BigDecimal

class PriceEmitterPresenterTest {

    @MockK
    lateinit var priceRepository: PriceRepository

    @MockK
    lateinit var view: PriceEmitterView

    lateinit var presenter: PriceEmitterPresenter

    private val testPrice = Price(Price.MARKET_NAME_GOLD, BigDecimal.ZERO, Direction.UNKNOWN)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        presenter = spyk(
            PriceEmitterPresenter(
                priceRepository,
                TestSchedulerProviderImpl()
            )
        )
        presenter.view = view

        mockkStatic(Timber::class)
    }

    @Test
    fun onFirstBindInitial() {
        every { priceRepository.prices(any()) } returns Flowable.just(testPrice)

        presenter.onFirstBind()

        verify {
            Timber.i(any<String>())
            presenter.getPricesAtMarketIndex()
        }
    }

    @Test
    fun onFirstBindStreaming() {
        every { priceRepository.prices(any()) } returns Flowable.just(testPrice)
        presenter.currentPriceValue = BigDecimal.TEN

        presenter.onFirstBind()

        verify {
            Timber.i(any<String>())
            presenter.getPricesAtMarketIndex()
            view.updatePrice(testPrice)
        }
    }
}