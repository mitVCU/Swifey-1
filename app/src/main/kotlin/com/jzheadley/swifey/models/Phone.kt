package com.jzheadley.swifey.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Phone(
        var countryCode: Int,
        var areaCode: Int,
        var exchangeNum: Int,
        var lineNum: Int
) : Parcelable