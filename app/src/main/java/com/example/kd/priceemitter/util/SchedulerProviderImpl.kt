package com.example.kd.priceemitter.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SchedulerProviderImpl @Inject constructor() : SchedulerProvider {
    override fun ui() = AndroidSchedulers.mainThread()

    override fun computation(): Scheduler = Schedulers.computation()
}