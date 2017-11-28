package com.jzheadley.swifey;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jzheadley.swifey.Models.Restaurant;
import com.jzheadley.swifey.adapter.RestaurantListAdapter;

import java.util.ArrayList;


public class RestaurantListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Restaurant> restaurants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        restaurants = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //////////////////////////////////////////
        Restaurant res1 = new Restaurant( Math.random(),"test1", "something", "9-5","");
        Restaurant res2 = new Restaurant( Math.random(),"test2", "something", "9-5","");
        Restaurant res3 = new Restaurant( Math.random(),"test3", "something", "9-5","");
        restaurants.add(res1);
        restaurants.add(res2);
        restaurants.add(res3);
        //////////////////////////////////////////

        // specify an RestaurantAdapter (see also next example)
        mAdapter = new RestaurantListAdapter( getApplicationContext(),restaurants);
        mRecyclerView.setAdapter(mAdapter);
    }
}
