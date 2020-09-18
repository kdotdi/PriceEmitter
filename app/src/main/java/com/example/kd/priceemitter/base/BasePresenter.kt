package com.example.kd.priceemitter.base

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

abstract class BasePresenter<V> : ViewModel() where V : BaseView {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    var view: V? = null

    var isFirstBind = true

    var latestViewChanges: Queue<V.() -> Unit> = LinkedList()

    private val compositeDisposable = CompositeDisposable()

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    val computationScheduler = Schedulers.computation()

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    val uiThread = AndroidSchedulers.mainThread()

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    open fun onFirstBind() {

    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    open fun onViewRestoreState() {

    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    fun present(
        viewChange: (V.() -> Unit)?
    ) =
        view?.let {
            viewChange?.invoke(it)
        } ?: run {
            latestViewChanges.add(viewChange)
        }

    fun bind(view: V) {
        if (this.view != null) {
            throw RuntimeException(
                "Concurrent view bind! Unexpected, second instance of view occurred before first one unbound."
            )
        }
        this.view = view
        if (isFirstBind) {
            isFirstBind = false
            onFirstBind()
        } else {
            onViewRestoreState()
        }
        while (!latestViewChanges.isEmpty()) {
            present(latestViewChanges.poll())
        }
    }

    open fun unbind() {
        this.view = null
        clearSubscriptions()
    }

    override fun onCleared() {
        finish()
        super.onCleared()
    }

    open fun finish() {
        clearSubscriptions()
    }

    fun addSubscription(disposable: Disposable) = compositeDisposable.add(disposable)

    fun clearSubscriptions() = compositeDisposable.clear()
}
