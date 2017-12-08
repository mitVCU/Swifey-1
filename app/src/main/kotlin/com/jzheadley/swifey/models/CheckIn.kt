package com.jzheadley.swifey.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.sql.Timestamp

@SuppressLint("ParcelCreator")
@Parcelize
data class CheckIn(
        val checkInId: Int?,
        var checkInDate: Timestamp,
        var maxNumOrders: Int,
        var acceptingOrders: Boolean?,
        val checkedInUser: User?,
        val restaurantCheckedInAt: Restaurant?,
        var orders: List<Order>) : Parcelable