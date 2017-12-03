package com.jzheadley.swifey.models

import java.sql.Timestamp


data class Order(
        val orderId: Int,
        var orderDate: Timestamp,
        var specialRequest: String,
        var checkIn: CheckIn?,
        var orderedMeal: Meal?,
        var user: User?
)