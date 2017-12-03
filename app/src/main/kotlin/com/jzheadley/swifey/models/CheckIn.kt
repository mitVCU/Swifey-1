package com.jzheadley.swifey.models

import java.sql.Timestamp

data class CheckIn(
        val checkInId: Int?,
        var checkInDate: Timestamp,
        var maxNumOrders: Int,
        val checkedInUser: User?,
        val restaurantCheckedInAt: Restaurant?,
        var orders: Set<Order>)