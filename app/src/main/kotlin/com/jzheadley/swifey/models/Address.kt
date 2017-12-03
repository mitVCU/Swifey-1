package com.jzheadley.swifey.models

data class Address(
        val addressId: Int,
        var buildingNumber: Int,
        var streetName: String,
        var city: String,
        var state: String,
        var zipCode: Int
)