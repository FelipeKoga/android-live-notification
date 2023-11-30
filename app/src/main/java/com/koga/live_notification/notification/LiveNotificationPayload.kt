package com.koga.live_notification.notification

data class LiveNotificationPayload(
    val progress: Int,
    val title: String,
    val description: String,
)
