package com.jzheadley.swifey.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Restaurant(
        val restaurantId: Int,
        var restaurantName: String,
        var restaurantPhotoUrl: String,
        var address: Address,
        var phone: Phone,
        var hours: Hours,
        var swipeTimes: List<SwipeTime>
) : Parcelable