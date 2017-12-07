package com.jzheadley.swifey.network.notifications

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class MessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        Timber.d("From: %s", remoteMessage!!.from)
        Timber.d("Notification Message Body: %s", remoteMessage.notification.body!!)
        Timber.d("RemoteMessage object:\t%s", remoteMessage)
    }
}
