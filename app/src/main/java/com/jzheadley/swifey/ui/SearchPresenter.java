package com.jzheadley.swifey.ui;

import com.jzheadley.swifey.models.User;
import com.jzheadley.swifey.network.SwifeyApi;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by mit on 12/7/17.
 */

public class SearchPresenter {
    private final SearchActivity activity;
    private final SwifeyApi api;

    public SearchPresenter(SwifeyApi api, SearchActivity activity) {
        this.api = api;
        this.activity = activity;
    }

    public void getSearch(){
        api.getSearch(activity.getSearchText())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Timber.d("subscribing for user");
                    }

                    @Override
                    public void onNext(List<User> users) {
                        Timber.v("Got users back"+users.toString());
                        activity.setUsers(users);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.wtf(e, "WTF Happened");
                    }

                    @Override
                    public void onComplete() {
                        Timber.v("Finished Getting users");

                    }
                });


    }
}
