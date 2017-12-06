package com.jzheadley.swifey.models

data class Restaurant(
        val restaurantId: Int,
        var restaurantName: String,
        var restaurantPhotoUrl: String,
        var address: Address,
        var phone: Phone,
        var hours: Hours,
        var swipeTimes: List<SwipeTime>
)