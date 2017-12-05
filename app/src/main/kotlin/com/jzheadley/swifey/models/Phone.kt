package com.jzheadley.swifey.models

data class Phone(
        var countryCode: Int,
        var areaCode: Int,
        var exchangeNum: Int,
        var lineNum: Int
)