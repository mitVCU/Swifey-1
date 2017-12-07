package com.jzheadley.swifey.network.notifications

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import com.jzheadley.swifey.network.SwifeyApi
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class FirebaseIDService : FirebaseInstanceIdService() {

    @Inject
    lateinit var api: SwifeyApi

    override fun onTokenRefresh() {
        // Get updated InstanceID token.
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Timber.d("Refreshed token: %s", refreshedToken!!)

        // TODO: Implement this method to send any registration to your app's servers.
        sendRegistrationToServer(refreshedToken)
    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private fun sendRegistrationToServer(token: String?) {
        Timber.v("The new FirebaseId is:\t%s", token)
        val uid = FirebaseAuth.getInstance().uid
        if (uid != null) {
            api.setUserMessagingId(uid, token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<Void> {
                        override fun onComplete() {
                            Timber.v("Finished sending registration to server")
                        }

                        override fun onSubscribe(d: Disposable) {
                            Timber.v("Finished subscribing to server")
                        }

                        override fun onNext(t: Void) {
                            Timber.v("Submitted to server")
                        }

                        override fun onError(e: Throwable) {
                            Timber.wtf(e, "WTF happened this is in the notification registration")
                        }

                    })
        }
    }
}