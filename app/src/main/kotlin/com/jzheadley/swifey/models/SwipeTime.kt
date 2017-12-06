package com.jzheadley.swifey.models

import java.sql.Time

data class SwipeTime(
        val swipeTimeId: Int,
        var startTime: Time,
        var endTime: Time,
        var meal: Meal?)