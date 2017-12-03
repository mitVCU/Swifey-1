package com.jzheadley.swifey.models

data class Phone(
        val phoneId: Int,
        var countryCode: Int,
        var areaCode: Int,
        var exchangeNum: Int,
        var lineNum: Int
)