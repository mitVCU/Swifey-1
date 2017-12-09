package com.jzheadley.swifey.ui

import com.jzheadley.swifey.models.Order
import com.jzheadley.swifey.network.SwifeyApi
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class OrderReviewPresenter(val activity: OrderReviewActivity, val api: SwifeyApi) {
    fun submitOrder(order: Order) {
        api.postOrder(order)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Void> {
                    override fun onComplete() {
                        Timber.v("Finished sending the order")
                        activity.finish()
                    }

                    override fun onSubscribe(d: Disposable) {
                        Timber.v("Subscribed to the response of submitting the order")
                    }

                    override fun onError(e: Throwable) {
                        Timber.wtf(e, "Something wen't wrong in submitting the order")
                    }

                    override fun onNext(t: Void) {
                        Timber.v("Submitting the order now")
                    }

                })
    }

}