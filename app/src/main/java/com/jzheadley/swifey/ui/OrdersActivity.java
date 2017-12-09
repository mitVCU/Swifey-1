package com.jzheadley.swifey.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.jzheadley.swifey.R;
import com.jzheadley.swifey.base.BaseApplication;
import com.jzheadley.swifey.models.CheckIn;
import com.jzheadley.swifey.models.Order;
import com.jzheadley.swifey.network.SwifeyApi;
import com.jzheadley.swifey.ui.adapter.OrdersAdapter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class OrdersActivity extends AppCompatActivity {
    @Inject
    SwifeyApi api;
    private CheckIn checkIn;
    private List<Order> orders;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button closeCheckIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        ((BaseApplication) getApplication()).getNetComponent().inject(this);
        checkIn = getIntent().getParcelableExtra("checkIn");
        orders = checkIn.getOrders();
        mRecyclerView = findViewById(R.id.order_recycler_view);
        closeCheckIn = findViewById(R.id.close_checkin_btn);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new OrdersAdapter(this, orders);
        mRecyclerView.setAdapter(mAdapter);

        if (checkIn.getAcceptingOrders()) {

            closeCheckIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    api.closeCheckIn(checkIn.getCheckInId())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<Void>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    Timber.v("we are subscribing");
                                }

                                @Override
                                public void onNext(Void aVoid) {
                                    Timber.v("got a response");
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Timber.wtf(e);
                                }

                                @Override
                                public void onComplete() {
                                    Timber.v("Le Fin");

                                }
                            });
                }
            });

        } else
            closeCheckIn.setVisibility(View.GONE);
    }
}
