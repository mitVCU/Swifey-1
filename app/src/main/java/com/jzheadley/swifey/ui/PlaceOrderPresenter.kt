package com.jzheadley.swifey.ui

import com.jzheadley.swifey.models.Meal
import com.jzheadley.swifey.network.SwifeyApi
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class PlaceOrderPresenter(val activity: PlaceOrderActivity, val api: SwifeyApi) {

    fun getRestaurantsMeals(restaurantId: Int?) {
        api.getRestaurantMeals(restaurantId!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<Meal>> {
                    override fun onNext(meals: List<Meal>) {
                        activity.setMeals(meals)
                    }

                    override fun onComplete() {
                        Timber.v("Finished getting meals for the restaurant")
                    }

                    override fun onError(e: Throwable) {
                        Timber.wtf(e, "Something went worng in getting the meals of the restaurant")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Timber.v("Subscribed to the response of getting the meals of the restaurant")
                    }

                })
    }
}