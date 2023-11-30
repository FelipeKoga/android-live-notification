package com.koga.live_notification.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.koga.live_notification.R
import com.koga.live_notification.notification.LiveNotificationManager.createDefaultNotificationLayout
import com.koga.live_notification.util.notificationManager

object LiveNotificationManager {
    private const val CHANNEL_ID = "live_notification_channel_id"
    private const val CHANNEL_NAME = "live_notification_channel_name"
    private const val NOTIFICATION_TAG = "live_notification_tag"
    const val NOTIFICATION_TOPIC = "live_notification"

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
            .setCustomBigContentView(createDefaultNotificationLayout(payload))
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setOngoing(true)
            .setSilent(true)
            .setAutoCancel(false)

        return builder.build()
    }

    private fun Context.createDefaultNotificationLayout(
        payload: LiveNotificationPayload
    ): RemoteViews {
        val view = RemoteViews(packageName, R.layout.notification_layout)

        view.setTextViewText(R.id.tv_title, payload.title)
        view.setTextViewText(R.id.tv_description, payload.description)

        when (payload.step) {
            LiveNotificationPayload.Step.FIRST -> {
                view.setColor(R.id.iv_first_step, getActiveStepColor())
            }

            LiveNotificationPayload.Step.SECOND -> {
                view.updateStepProgressBar(R.id.pb_first)
                view.setColor(R.id.iv_second_step, getActiveStepColor())
            }

            LiveNotificationPayload.Step.THIRD -> {
                view.updateStepProgressBar(R.id.pb_first)
                view.updateStepProgressBar(R.id.pb_second)
                view.setColor(R.id.iv_third_step, getActiveStepColor())
            }
        }

        return view
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

    private fun RemoteViews.updateStepProgressBar(
        layoutId: Int,
        progress: Int = 100,
    ) {
        setProgressBar(
            layoutId,
            100,
            progress,
            false
        )
    }

    private fun RemoteViews.setColor(
        layoutId: Int,
        color: Int,
    ) {
        setInt(
            layoutId,
            "setColorFilter",
            color
        )
    }

    private fun Context.getActiveStepColor(): Int {
        return ContextCompat.getColor(this, R.color.teal_700)
    }
}