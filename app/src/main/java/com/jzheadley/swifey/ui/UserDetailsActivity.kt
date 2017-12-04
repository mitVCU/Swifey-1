package com.jzheadley.swifey.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.jzheadley.swifey.R
import com.jzheadley.swifey.base.BaseApplication
import com.jzheadley.swifey.exceptions.EmptyInputException
import com.jzheadley.swifey.models.Phone
import com.jzheadley.swifey.models.User
import kotlinx.android.synthetic.main.activity_user_details.*
import timber.log.Timber
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


class UserDetailsActivity : AppCompatActivity() {
    var currentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        (application as BaseApplication).netComponent.inject(this)
        currentUser = FirebaseAuth.getInstance().currentUser
        val calendar: Calendar = Calendar.getInstance()
        Timber.v("Setting onDateSetListener")
        val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }
        Timber.v("onDateSetListener set")
        Timber.v("Setting on click listener")
        dob.setOnFocusChangeListener({ view: View, b: Boolean ->
            DatePickerDialog(this, date, calendar
                    .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
        })
        Timber.v("on click listener set")
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.submit_user_details_btn -> {
                submitUserDetails()
            }
        }
    }

    private fun submitUserDetails() {
        try {
            val user = User(currentUser?.uid!!, getFirstName(), getLastName(), getDOB(), getCreationDate(), getNumSwipes(), getPhone(), listOf(), listOf(), listOf())
            Timber.v("UserDetails:\t%s", user.toString())
        } catch (exception: EmptyInputException) {
            Timber.e(exception, "One of the input fields was empty")

        }

    }

    private fun getNumSwipes(): Int {
        if (TextUtils.isEmpty(num_swipes.text.toString())) {
            throw EmptyInputException("The number of swipes was left empty")
        } else {
            return num_swipes.text.toString().toInt()
        }
    }

    private fun getFirstName(): String = first_name.text.toString()

    private fun getLastName(): String = last_name.text.toString()

    private fun getDOB(): Date = Date.valueOf(dob.text.toString())

    private fun getCreationDate(): Timestamp = Timestamp(System.currentTimeMillis())

    private fun getPhone(): Phone {
        val phoneText: String = phone_number.text.toString().replace("[^0-9]+", "")
        if (TextUtils.isEmpty(phoneText)) {
            throw EmptyInputException("The Phone number was left empty")
        } else if () {
        } else {
            return Phone(phoneText.substring(0, 3).toInt(), phoneText.substring(3, 6).toInt(), phoneText.substring(6, 10).toInt())
        }
    }


    private fun updateLabel() {
        val format = "MM/dd/yy" //In which you need put here
        val sdf = SimpleDateFormat(format, Locale.US)
        dob.setText(sdf.format(Calendar.getInstance().time))
    }

    private fun setupPhoneInput() {

    }
}
