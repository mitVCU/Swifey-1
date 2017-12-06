package com.jzheadley.swifey.ui;

import com.jzheadley.swifey.models.Meal;
import com.jzheadley.swifey.network.SwifeyApi;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MealPresenter {
    private final MealActivity activity;
    private final SwifeyApi api;

    public MealPresenter(SwifeyApi api, MealActivity activity) {
        this.api = api;
        this.activity = activity;
    }

    public void getRestaurantsMeals(int restaurantId) {
        Timber.v("RestaurantId:\t%s", restaurantId);
        api.getRestaurantMeals(restaurantId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Meal>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Timber.v("Subscribed");
                    }

                    @Override
                    public void onNext(List<Meal> meals) {
                        activity.setMeals(meals);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.wtf(e, "Why did we error");
                    }

                    @Override
                    public void onComplete() {
                        Timber.v("Finished getting meals for the restaurant");
                    }
                });
    }
}
