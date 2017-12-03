package com.jzheadley.swifey.models

import java.sql.Timestamp

data class SwipeTime(
        val swipeTimeId: Int,
        var startTime: Timestamp,
        var endTime: Timestamp,
        var meal: Meal?)