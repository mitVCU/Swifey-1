package com.jzheadley.swifey.models

data class Meal(
        val mealId: Int,
        var mealName: String,
        var mealDesc: String,
        var mealCost: Int,
        var restaurant: Restaurant?
)