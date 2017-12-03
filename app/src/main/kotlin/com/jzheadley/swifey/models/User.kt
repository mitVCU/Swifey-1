package com.jzheadley.swifey.models

import java.sql.Date
import java.sql.Timestamp

data class User(
        var userId: String,
        var firstName: String,
        var lastName: String,
        var dob: Date,
        var creationDate: Timestamp,
        var numSwipes: Int,
        var phone: Phone,
        var checkIns: List<CheckIn>,
        var followers: List<User>,
        var following: List<User>
)