package com.jzheadley.swifey.models

import android.os.Parcel
import android.os.Parcelable

data class Meal(
        val mealId: Int,
        var mealName: String,
        var mealDesc: String,
        var mealCost: Int
//        var restaurant: Restaurant?
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(mealId)
        writeString(mealName)
        writeString(mealDesc)
        writeInt(mealCost)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Meal> = object : Parcelable.Creator<Meal> {
            override fun createFromParcel(source: Parcel): Meal = Meal(source)
            override fun newArray(size: Int): Array<Meal?> = arrayOfNulls(size)
        }
    }
}