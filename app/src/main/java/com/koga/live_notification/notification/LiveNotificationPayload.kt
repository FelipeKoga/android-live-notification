package com.koga.live_notification.notification

data class LiveNotificationPayload(
    val id: Int,
    val title: String,
    val description: String,
    val step: Step,
) {
    enum class Step(val value: String) {
        FIRST("first_step"),
        SECOND("second_step"),
        THIRD("third_step");

        companion object {
            fun get(value: String): Step {
                return entries.firstOrNull { it.value == value } ?: FIRST
            }
        }
    }
}
