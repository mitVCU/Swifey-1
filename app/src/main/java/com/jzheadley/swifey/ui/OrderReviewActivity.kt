package com.jzheadley.swifey.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import com.google.firebase.auth.FirebaseAuth
import com.jzheadley.swifey.R
import com.jzheadley.swifey.base.BaseApplication
import com.jzheadley.swifey.models.*
import com.jzheadley.swifey.network.SwifeyApi
import com.jzheadley.swifey.ui.adapter.OrderReviewAdapter
import kotlinx.android.synthetic.main.activity_order_review.*
import timber.log.Timber
import java.sql.Timestamp
import java.util.*
import javax.inject.Inject

class OrderReviewActivity : AppCompatActivity() {
    @Inject
    lateinit var api: SwifeyApi
    private var orderReviewAdapter: OrderReviewAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var selectedMeals: List<Meal>? = ArrayList()
    var presenter: OrderReviewPresenter? = null
    private var checkIn: CheckIn? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_review)
        (application as BaseApplication).netComponent.inject(this)
        selectedMeals = intent.extras["selectedMeals"] as List<Meal>
        checkIn = intent.extras["checkIn"] as CheckIn
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
        place_order_btn.setOnClickListener {
            presenter?.submitOrder(Order(null, Timestamp(System.currentTimeMillis()), special_requests.text.toString(), checkIn, selectedMeals,
                    User(FirebaseAuth.getInstance().currentUser?.uid, null, null, null, null, null, null, null, null, null, null)))
        }
        validate_discount_btn.setOnClickListener({
            val discountCode = discount_code_et.text.toString();
            if (TextUtils.isEmpty(discountCode)) {
                discount_code_et.error = "Please enter a discount code"
            } else {
                presenter?.validateDiscountCode(DiscountCheckDTO(discountCode, selectedMeals))
            }
        })
    }

    private fun getTotalCost(meals: List<Meal>) = meals.map(Meal::mealCost).sum()

    fun handleValidateDiscountResponse(response: Boolean) {
        Timber.v("Validating the response of the Discount check:\t%s", response)
    }
}
