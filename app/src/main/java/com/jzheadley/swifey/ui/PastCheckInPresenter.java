package com.jzheadley.swifey.ui;

import com.google.firebase.auth.FirebaseAuth;
import com.jzheadley.swifey.models.CheckIn;
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

class PastCheckInPresenter {
    private final PastCheckInActivity activity;
    private final SwifeyApi api;

    public PastCheckInPresenter(SwifeyApi api, PastCheckInActivity activity){
        this.activity = activity;
        this.api = api;
    }

    public void getCheckInById(){
        String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        api.getCheckInById("IFxX0KIatEbijblNa22S7O5WeMw1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CheckIn>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Timber.v("Subscribed to checkins");
                    }

                    @Override
                    public void onNext(List<CheckIn> checkIns) {
                        Timber.v("we slide in the checks in");
                        activity.setCheckIns(checkIns);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.wtf(e, "WTF Happened");
                    }

                    @Override
                    public void onComplete() {
                        Timber.v("Finished Getting old checkins");
                    }
                });

    }

}
