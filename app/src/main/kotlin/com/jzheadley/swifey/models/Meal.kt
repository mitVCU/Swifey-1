package com.jzheadley.swifey.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Meal(
        val mealId: Int,
        var mealName: String,
        var mealDesc: String,
        var mealCost: Int
//        var restaurant: Restaurant?
) : Parcelable