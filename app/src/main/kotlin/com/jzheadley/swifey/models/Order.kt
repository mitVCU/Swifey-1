package com.jzheadley.swifey.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.sql.Timestamp


@SuppressLint("ParcelCreator")
@Parcelize
data class Order(
        val orderId: Int,
        var orderDate: Timestamp,
        var specialRequest: String,
        var checkIn: CheckIn?,
        var orderedMeal: Meal?,
        var user: User?
) : Parcelable