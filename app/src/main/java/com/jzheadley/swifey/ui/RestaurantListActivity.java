package com.jzheadley.swifey.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jzheadley.swifey.R;
import com.jzheadley.swifey.base.BaseApplication;
import com.jzheadley.swifey.models.Restaurant;
import com.jzheadley.swifey.network.SwifeyApi;
import com.jzheadley.swifey.ui.adapter.RestaurantListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;


public class RestaurantListActivity extends AppCompatActivity {
    @Inject
    SwifeyApi api;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Restaurant> restaurants;
    private RestaurantListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        ((BaseApplication) getApplication()).getNetComponent().inject(this);
        presenter = new RestaurantListPresenter(api, this);
        restaurants = new ArrayList<>();
        mRecyclerView = findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RestaurantListAdapter(getApplicationContext(), restaurants);
        mRecyclerView.setAdapter(mAdapter);
        presenter.getTodaysRestaurants();
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        Timber.v("Setting restaurants for today to:	%s", restaurants);
        mAdapter = new RestaurantListAdapter(getApplicationContext(), restaurants);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
