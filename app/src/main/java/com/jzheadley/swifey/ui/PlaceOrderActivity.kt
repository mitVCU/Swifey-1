package com.jzheadley.swifey.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
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
    private var mealAdapter: MealListAdapter? = null

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
        mealAdapter = MealListAdapter(this, meals)
        meal_recycler_view.adapter = mealAdapter
        presenter?.getRestaurantsMeals(checkIn?.restaurantCheckedInAt?.restaurantId)

    }

    fun onClick(view: View) {
        Timber.v("The selected meals are:\t%s", mealAdapter?.selectedMeals)
        Timber.v("The user has selected %s meals.", mealAdapter?.selectedMeals?.count())
        val intent = Intent(this, OrderReviewActivity::class.java)
        intent.putExtra("selectedMeals", mealAdapter?.selectedMeals)
        intent.putExtra("checkIn", checkIn)
        startActivity(intent)
    }

    fun setMeals(meals: List<Meal>) {
        this.meals = meals
        mealAdapter = MealListAdapter(this, meals)
        meal_recycler_view.adapter = mealAdapter

    }
}
