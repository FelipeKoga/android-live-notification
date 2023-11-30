package com.koga.live_notification

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.messaging.FirebaseMessaging
import com.koga.live_notification.notification.LiveNotificationManager
import com.koga.live_notification.notification.LiveNotificationManager.NOTIFICATION_TOPIC
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
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedContent(
            targetState = subscribed,
            label = ""
        ) { target ->
            when {
                target -> SubscribedUI()

                else -> {
                    Button(
                        onClick = {
                            FirebaseMessaging.getInstance().subscribeToTopic(NOTIFICATION_TOPIC)
                            subscribed = true
                        }
                    ) {
                        Text(text = "Subscribe to topic")
                    }
                }
            }
        }
    }
}

@Composable
fun SubscribedUI() {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = "Subscribed!",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Run the script from the 'fcm-script' folder to create your live notification",
            style = MaterialTheme.typography.headlineSmall.copy(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal
            )
        )
    }
}

@Preview
@Composable
fun SubscribedUIPreview() {
    SubscribedUI()
}