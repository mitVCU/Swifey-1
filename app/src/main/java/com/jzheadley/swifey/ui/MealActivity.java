package com.jzheadley.swifey.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jzheadley.swifey.R;
import com.jzheadley.swifey.base.BaseApplication;
import com.jzheadley.swifey.models.Meal;
import com.jzheadley.swifey.models.Restaurant;
import com.jzheadley.swifey.network.SwifeyApi;
import com.jzheadley.swifey.ui.adapter.MealListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class MealActivity extends AppCompatActivity {
    private static final String TAG = "MealActivity";

    @Inject
    SwifeyApi api;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Meal> meals;
    private MealPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_list);
        meals = new ArrayList<>();
        mRecyclerView = findViewById(R.id.meal_recycler_view);
        Restaurant restaurant = getIntent().getParcelableExtra("restaurant");
        ((BaseApplication) getApplication()).getNetComponent().inject(this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        presenter = new MealPresenter(api, this);
        mAdapter = new MealListAdapter(getApplicationContext(), meals);
        mRecyclerView.setAdapter(mAdapter);
        Timber.v("RestaurantId:\t%s", restaurant);
        presenter.getRestaurantsMeals(restaurant.getRestaurantId());
        setupUi(restaurant);
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
        mAdapter = new MealListAdapter(getApplicationContext(), meals);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setupUi(Restaurant restaurant) {
        ((TextView) findViewById(R.id.restaurant_name)).setText(restaurant.getRestaurantName());
        Glide.with(this)
                .load(restaurant.getRestaurantPhotoUrl())
                .into((ImageView) findViewById(R.id.restaurant_image));

    }
}
