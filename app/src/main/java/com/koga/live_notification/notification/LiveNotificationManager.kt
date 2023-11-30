package com.koga.live_notification.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.koga.live_notification.R
import com.koga.live_notification.util.notificationManager

object LiveNotificationManager {
    private const val CHANNEL_ID = "live_notification_channel_id"
    private const val CHANNEL_NAME = "live_notification_channel_name"
    private const val NOTIFICATION_TAG = "live_notification_tag"

    fun showNotification(context: Context, payload: LiveNotificationPayload) {
        val notification = context.createNotification(payload)
        context.notificationManager.notify(
            NOTIFICATION_TAG,
            1,
            notification
        )
    }

    private fun Context.createNotification(
        payload: LiveNotificationPayload
    ): Notification {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setCustomContentView(createDefaultNotificationLayout(payload))
            .setCustomBigContentView(createExpandedNotificationLayout(payload))
            .setCustomHeadsUpContentView(createHeadsUpNotificationLayout(payload))
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setOngoing(true)
            .setSilent(true)
            .setAutoCancel(false)

        return builder.build()
    }

    private fun Context.createDefaultNotificationLayout(payload: LiveNotificationPayload): RemoteViews {
        val notificationLayout = RemoteViews(packageName, R.layout.notification_layout)

        notificationLayout.setTextViewText(R.id.tv_title, payload.title)
        notificationLayout.setTextViewText(R.id.tv_description, payload.description)
        notificationLayout.setProgressBar(
            R.id.progressBar,
            100,
            payload.progress,
            false
        )

        return notificationLayout
    }

    private fun Context.createExpandedNotificationLayout(payload: LiveNotificationPayload): RemoteViews {
        val notificationLayout = RemoteViews(packageName, R.layout.notification_layout)

        notificationLayout.setTextViewText(R.id.tv_title, payload.title)
        notificationLayout.setTextViewText(R.id.tv_description, payload.description)
        notificationLayout.setProgressBar(
            R.id.progressBar,
            100,
            payload.progress,
            false
        )

        return notificationLayout
    }

    private fun Context.createHeadsUpNotificationLayout(payload: LiveNotificationPayload): RemoteViews {
        val notificationLayout = RemoteViews(packageName, R.layout.notification_layout)

        notificationLayout.setTextViewText(R.id.tv_title, payload.title)
        notificationLayout.setTextViewText(R.id.tv_description, payload.description)
        notificationLayout.setProgressBar(
            R.id.progressBar,
            100,
            payload.progress,
            false
        )

        return notificationLayout
    }

    fun createNotificationChannel(context: Context) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            setSound(null, null)
        }

        context.notificationManager.createNotificationChannel(channel)
    }

}