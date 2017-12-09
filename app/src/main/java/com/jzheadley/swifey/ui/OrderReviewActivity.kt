package com.jzheadley.swifey.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jzheadley.swifey.R
import com.jzheadley.swifey.base.BaseApplication
import com.jzheadley.swifey.models.Meal
import com.jzheadley.swifey.models.Order
import com.jzheadley.swifey.network.SwifeyApi
import com.jzheadley.swifey.ui.adapter.OrderReviewAdapter
import kotlinx.android.synthetic.main.activity_order_review.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class OrderReviewActivity : AppCompatActivity() {
    @Inject
    lateinit var api: SwifeyApi
    private var orderReviewAdapter: OrderReviewAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var selectedMeals: List<Meal>? = ArrayList()
    var presenter: OrderReviewPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_review)
        (application as BaseApplication).netComponent.inject(this)
        selectedMeals = intent.extras["selectedMeals"] as List<Meal>
        Timber.v("The selected meals are:\t%s", selectedMeals)
        presenter = OrderReviewPresenter(this, api)
        layoutManager = LinearLayoutManager(this)
        selected_meals_rv.layoutManager = layoutManager
        orderReviewAdapter = OrderReviewAdapter(this, selectedMeals)
        selected_meals_rv.adapter = orderReviewAdapter
        setupUI(selectedMeals)
    }

    private fun setupUI(meals: List<Meal>?) {
        val cost = getTotalCost(meals!!)
        order_cost_tv.text = String.format(resources.getQuantityString(R.plurals.order_cost_tv, cost), cost)
        place_order_btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                presenter.submitOrder(Order())
            }

        })
    }

    private fun getTotalCost(meals: List<Meal>) = meals.map(Meal::mealCost).sum()
}
