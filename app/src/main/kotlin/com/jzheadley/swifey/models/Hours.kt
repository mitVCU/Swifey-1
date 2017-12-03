package com.jzheadley.swifey.models

import java.sql.Time

data class Hours(
        val hoursId: Int,
        var openTime: Time,
        var closeTime: Time,
        var dayOfWeek: String
)