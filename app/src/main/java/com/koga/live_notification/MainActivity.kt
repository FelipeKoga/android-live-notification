package com.koga.live_notification

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.firebase.messaging.FirebaseMessaging
import com.koga.live_notification.notification.LiveNotificationManager
import com.koga.live_notification.ui.theme.PocLiveNotificationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LiveNotificationManager.createNotificationChannel(this)

        setContent {
            PocLiveNotificationTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var subscribed by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            enabled = !subscribed,
            onClick = {
                FirebaseMessaging.getInstance().subscribeToTopic("real_time_notification")
                subscribed = true
            }
        ) {
            Text(
                text = when (subscribed) {
                    true -> "Subscribed. Execute the script to generate a custom live notification"
                    else -> "Subscribe to topic"
                }
            )
        }
    }
}