package com.jzheadley.swifey.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.gson.Gson
import com.jzheadley.swifey.R
import com.jzheadley.swifey.base.BaseApplication
import com.jzheadley.swifey.models.CheckIn
import com.jzheadley.swifey.models.Meal
import com.jzheadley.swifey.network.SwifeyApi
import com.jzheadley.swifey.ui.adapter.MealListAdapter
import kotlinx.android.synthetic.main.activity_place_order.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class PlaceOrderActivity : AppCompatActivity() {
    @Inject
    lateinit var api: SwifeyApi
    @Inject
    lateinit var gson: Gson

    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private var meals: List<Meal>? = ArrayList()
    private var checkIn: CheckIn? = null
    private var presenter: PlaceOrderPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_order)
        (this.application as BaseApplication).netComponent.inject(this)
        presenter = PlaceOrderPresenter(this, api)
        Timber.v("Extras of the PlaceOrderActivity:\t%s", intent.extras.keySet().toList().toString())
        mLayoutManager = LinearLayoutManager(this)
        checkIn = gson.fromJson(intent.extras.getString("checkIn"), CheckIn::class.java)
        Timber.v("The check in was:\t%s", checkIn)
        meal_recycler_view.layoutManager = mLayoutManager
        meal_recycler_view.adapter = MealListAdapter(this, meals)
        presenter?.getRestaurantsMeals(checkIn?.restaurantCheckedInAt?.restaurantId)

    }

    fun setMeals(meals: List<Meal>) {
        this.meals = meals
        meal_recycler_view.adapter = MealListAdapter(this, meals)
    }
}
