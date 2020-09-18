package com.example.kd.priceemitter.view

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.kd.priceemitter.R
import com.example.kd.priceemitter.base.BasePresenterActivity
import com.example.kd.priceemitter.base.PresenterFactory
import com.example.kd.priceemitter.domain.entity.Price
import com.example.kd.priceemitter.presenter.PriceEmitterPresenter
import com.example.kd.priceemitter.presenter.PriceEmitterView
import dagger.android.AndroidInjection
import javax.inject.Inject
import javax.inject.Provider
import kotlinx.android.synthetic.main.activity_main.*

class PriceEmitterActivity :
    BasePresenterActivity<PriceEmitterPresenter, PriceEmitterView>(),
    PriceEmitterView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        market_switch.setOnClickListener {
            presenter.switchMarket()
        }
    }

    override fun updatePrice(price: Price) {
        price_value.text = price.value.toString()
        direction.text = price.direction.toString()
        market_name.text = price.marketName
    }

    @Inject
    lateinit var priceEmitterPresenter: Provider<PriceEmitterPresenter>

    override fun injectDependencies() = AndroidInjection.inject(this)

    override fun onPresenterPrepared(fromStorage: Boolean) = Unit

    override fun presenterClass(): Class<PriceEmitterPresenter> = PriceEmitterPresenter::class.java

    override fun prepareFactory(): PresenterFactory {
        return object : PresenterFactory() {
            override fun <T : ViewModel> createPresenter(presenterClass: Class<T>): T {
                return priceEmitterPresenter.get() as T
            }
        }
    }
}
