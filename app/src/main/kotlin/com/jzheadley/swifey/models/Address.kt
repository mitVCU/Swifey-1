package com.jzheadley.swifey.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Address(
        val addressId: Int,
        var buildingNumber: Int,
        var streetName: String,
        var city: String,
        var state: String,
        var zipCode: Int
) : Parcelable