package com.koga.live_notification.notification

import android.annotation.SuppressLint
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FCMService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val data: Map<String, Any> = message.data

        if (data[NOTIFICATION_TYPE] == LIVE_NOTIFICATION) {
            LiveNotificationManager.showNotification(
                context = this,
                payload = LiveNotificationPayload(
                    step = LiveNotificationPayload.Step.get(data["step"].toString()),
                    title = data["title"].toString(),
                    description = data["description"].toString()
                )
            )
        }

    }

    companion object {
        private const val NOTIFICATION_TYPE = "type"
        private const val LIVE_NOTIFICATION = "LIVE_NOTIFICATION"
    }
}