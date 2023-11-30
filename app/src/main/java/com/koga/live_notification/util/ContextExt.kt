package com.koga.live_notification.util

import android.app.NotificationManager
import android.content.Context
import com.google.firebase.messaging.FirebaseMessagingService


val Context.notificationManager
    get() = getSystemService(FirebaseMessagingService.NOTIFICATION_SERVICE) as NotificationManager