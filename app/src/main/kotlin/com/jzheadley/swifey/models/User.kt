package com.jzheadley.swifey.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.sql.Date
import java.sql.Timestamp

@SuppressLint("ParcelCreator")
@Parcelize
data class User(
        var userId: String?,
        var firstName: String?,
        var lastName: String?,
        var dob: Date?,
        var creationDate: Timestamp?,
        var numSwipes: Int?,
        var phone: Phone?,
        var checkIns: List<CheckIn>?,
        var followers: List<User>?,
        var following: List<User>?
) : Parcelable