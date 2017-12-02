package com.jzheadley.swifey.ui.adapter;

import com.jzheadley.swifey.Models.Restaurant;
import com.jzheadley.swifey.network.SwifeyApi;
import com.jzheadley.swifey.ui.RestaurantListActivity;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

                    }

                    @Override
                    public void onNext(List<Restaurant> restaurants) {
                        activity.setRestaurants(restaurants);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
