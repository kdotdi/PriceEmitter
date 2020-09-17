package com.example.kd.priceemitter.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class PresenterFactory : ViewModelProvider.Factory {

    var isNewlyCreated = false
        private set

    override fun <T : ViewModel> create(presenterClass: Class<T>): T {
        try {
            val presenter = createPresenter(presenterClass)
            isNewlyCreated = true
            return presenter
        } catch (throwable: Throwable) {
            throw IllegalArgumentException("Failed to create presenter: ${presenterClass.name}, exception: ${throwable.message}")
        }
    }

    abstract fun <T : ViewModel> createPresenter(presenterClass: Class<T>): T
}
