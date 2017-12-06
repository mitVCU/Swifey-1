package com.jzheadley.swifey.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.sql.Time

@SuppressLint("ParcelCreator")
@Parcelize
data class SwipeTime(
        val swipeTimeId: Int,
        var startTime: Time,
        var endTime: Time,
        var meal: Meal?) : Parcelable