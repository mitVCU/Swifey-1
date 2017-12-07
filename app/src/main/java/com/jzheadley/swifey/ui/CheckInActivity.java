package com.jzheadley.swifey.ui;

import com.google.firebase.auth.FirebaseAuth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jzheadley.swifey.R;
import com.jzheadley.swifey.base.BaseApplication;
import com.jzheadley.swifey.models.CheckIn;
import com.jzheadley.swifey.models.Meal;
import com.jzheadley.swifey.models.Order;
import com.jzheadley.swifey.models.Restaurant;
import com.jzheadley.swifey.models.User;
import com.jzheadley.swifey.network.SwifeyApi;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class CheckInActivity extends AppCompatActivity {
    private static final String TAG = "CheckInActivity";

    @Inject
    SwifeyApi api;

    // private RecyclerView mRecyclerView;
    // private RecyclerView.Adapter mAdapter;
    // private RecyclerView.LayoutManager mLayoutManager;
    private List<Meal> meals;
    private CheckInPresenter presenter;
    private EditText maxOrdersET;
    private Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_list);
        meals = new ArrayList<>();
        // mRecyclerView = findViewById(R.id.meal_recycler_view);
        restaurant = getIntent().getParcelableExtra("restaurant");
        ((BaseApplication) getApplication()).getNetComponent().inject(this);
        // mLayoutManager = new LinearLayoutManager(this);
        // mRecyclerView.setLayoutManager(mLayoutManager);
        presenter = new CheckInPresenter(api, this);
        // mAdapter = new MealListAdapter(getApplicationContext(), meals);
        // mRecyclerView.setAdapter(mAdapter);
        Timber.v("RestaurantId:\t%s", restaurant);
        // presenter.getRestaurantsMeals(restaurant.getRestaurantId());
        setupUi(restaurant);
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
        // mAdapter = new MealListAdapter(getApplicationContext(), meals);
        // mRecyclerView.setAdapter(mAdapter);
    }

    public void setupUi(Restaurant restaurant) {
        ((TextView) findViewById(R.id.restaurant_name)).setText(restaurant.getRestaurantName());
        Glide.with(this)
                .load(restaurant.getRestaurantPhotoUrl())
                .into((ImageView) findViewById(R.id.restaurant_image));
        maxOrdersET = (EditText) findViewById(R.id.maxOrders_tiet);

    }

    public void onClick(View view) {
        // validate we have an entry for maxOrders and then submit it
        if (TextUtils.isEmpty(maxOrdersET.getText().toString())) {
            maxOrdersET.setError("You must enter a value for the maximum number of orders");
        } else if (!TextUtils.isDigitsOnly(maxOrdersET.getText().toString())) {
            maxOrdersET.setError("Invalid Input");
        } else {
            int maxOrders = Integer.parseInt(maxOrdersET.getText().toString());
            CheckIn checkIn = new CheckIn(null, new Timestamp(System.currentTimeMillis()), maxOrders,
                    new User(FirebaseAuth.getInstance().getUid(), null, null, null, null, null, null, null, null, null),
                    new Restaurant(restaurant.getRestaurantId(), restaurant.getRestaurantName(), null, null, null, null, null), new ArrayList<Order>());
            presenter.submitCheckIn(checkIn);
        }
    }
}
