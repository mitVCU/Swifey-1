package com.jzheadley.swifey.ui;

import com.jzheadley.swifey.models.Restaurant;
import com.jzheadley.swifey.network.SwifeyApi;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class RestaurantListPresenter {
    private final RestaurantListActivity activity;
    private final SwifeyApi api;

    public RestaurantListPresenter(SwifeyApi api, RestaurantListActivity activity) {
        this.api = api;
        this.activity = activity;
    }

    public void getTodaysRestaurants() {
        api.getTodaysRestaurants()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Restaurant>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Timber.v("Subscribed");
                    }

                    @Override
                    public void onNext(List<Restaurant> restaurants) {
                        Timber.v("Got some restaurants Back:\t %s", restaurants);
                        activity.setRestaurants(restaurants);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.wtf(e, "WTF Happened");
                    }

                    @Override
                    public void onComplete() {
                        Timber.v("Finished Getting restaurants");

                    }
                });
    }
}
