package com.koga.live_notification.notification

import android.annotation.SuppressLint
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FCMService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val data: Map<String, Any> = message.data

        LiveNotificationManager.showNotification(
            context = this,
            payload = LiveNotificationPayload(
                progress = data["progress"].toString().toInt(),
                title = data["title"].toString(),
                description = data["description"].toString()
            )
        )
    }
}