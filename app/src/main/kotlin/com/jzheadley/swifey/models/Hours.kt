package com.jzheadley.swifey.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.sql.Time

@SuppressLint("ParcelCreator")
@Parcelize
data class Hours(
        val hoursId: Int,
        var openTime: Time,
        var closeTime: Time,
        var dayOfWeek: String
) : Parcelable