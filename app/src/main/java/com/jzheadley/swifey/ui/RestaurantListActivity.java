package com.jzheadley.swifey.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jzheadley.swifey.Models.Restaurant;
import com.jzheadley.swifey.R;
import com.jzheadley.swifey.base.BaseApplication;
import com.jzheadley.swifey.network.SwifeyApi;
import com.jzheadley.swifey.ui.adapter.RestaurantListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


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
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //////////////////////////////////////////
        Restaurant res1 = new Restaurant(Math.random(), "Restaurant 1", "This is a description of the restaurant", "9-5", "");
        Restaurant res2 = new Restaurant(Math.random(), "Restaurant 2", "This is a description of the restaurant", "9-5", "");
        Restaurant res3 = new Restaurant(Math.random(), "Restaurant 3", "This is a description of the restaurant", "9-5", "");
        restaurants.add(res1);
        restaurants.add(res2);
        restaurants.add(res3);
        //////////////////////////////////////////

        // specify an RestaurantAdapter (see also next example)
        mAdapter = new RestaurantListAdapter(getApplicationContext(), (ArrayList<Restaurant>) restaurants);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
