package com.example.kd.priceemitter.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TestSchedulerProviderImpl @Inject constructor() : SchedulerProvider {
    override fun ui() = Schedulers.trampoline()

    override fun computation(): Scheduler = Schedulers.trampoline()
}