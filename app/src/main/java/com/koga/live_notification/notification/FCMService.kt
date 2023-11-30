package com.koga.live_notification.notification

import android.annotation.SuppressLint
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FCMService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val data: Map<String, Any> = message.data

        if (data[NOTIFICATION_TYPE] == LIVE_NOTIFICATION) {
            LiveNotificationManager.showNotification(
                context = this,
                payload = LiveNotificationPayload(
                    id = data["id"].toString().toIntOrNull() ?: Random.nextInt(),
                    title = data["title"].toString(),
                    description = data["description"].toString(),
                    step = LiveNotificationPayload.Step.get(data["step"].toString())
                )
            )
        }

    }

    companion object {
        private const val NOTIFICATION_TYPE = "type"
        private const val LIVE_NOTIFICATION = "LIVE_NOTIFICATION"
    }
}