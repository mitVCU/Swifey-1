package com.jzheadley.swifey.ui;

import com.jzheadley.swifey.models.User;
import com.jzheadley.swifey.network.SwifeyApi;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class UserDetailsPresenter {
    private SwifeyApi api;
    private UserDetailsActivity activity;

    public UserDetailsPresenter(SwifeyApi api, UserDetailsActivity activity) {
        this.api = api;
        this.activity = activity;
    }

    public void sendUserToServer(User user) {
        api.createUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        Timber.v(String.valueOf(user));
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
