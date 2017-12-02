package com.jzheadley.swifey.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jzheadley.swifey.Models.Meal;
import com.jzheadley.swifey.R;
import com.jzheadley.swifey.ui.adapter.MealListAdapter;

import java.util.ArrayList;

/**
 * Created by mit on 11/21/17.
 */

public class MealActivity extends AppCompatActivity{
    private static final String TAG = "MealActivity";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Meal> meals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_list);
        meals = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.meal_recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //////////////////////////////////
        Meal meal0 = new Meal((int)Math.random(), "test0", "something, something, blah, blah", 50850);
        Meal meal1 = new Meal((int)Math.random(), "test1", "something, something, blah, blah", 58900);
        Meal meal2 = new Meal((int)Math.random(), "test2", "something, something, blah, blah", 5500);

        meals.add(meal0);
        meals.add(meal1);
        meals.add(meal2);
        //////////////////////////////////
        Log.d(TAG,"we got meals");
        mAdapter = new MealListAdapter(getApplicationContext(), meals);
        mRecyclerView.setAdapter(mAdapter);

    }
}
