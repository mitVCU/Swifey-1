package com.jzheadley.swifey.network.notifications

import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.jzheadley.swifey.ui.PlaceOrderActivity
import timber.log.Timber

class MessagingService : FirebaseMessagingService() {


    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        Timber.d("From: %s", remoteMessage!!.from)
        Timber.d("Notification Message Body: %s", remoteMessage.notification.body!!)
        Timber.d("RemoteMessage object:\t%s", remoteMessage.toString())
        Timber.d("RemoteMessage object:\t%s", remoteMessage.data)
        if (!remoteMessage.data.isEmpty()) {
            val intent = Intent(this, PlaceOrderActivity::class.java)
            Timber.v("The checkIn looks like this:\t%s", remoteMessage.data["checkIn"])
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("checkIn", remoteMessage.data["checkIn"])
            this.startActivity(intent)
        }
    }
}
