package com.june.notification.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.june.notification.R
import com.june.notification.notification.Constants.Companion.TOUCH_PENDING_INTENT_ACTION
import com.june.notification.notification.touchevent.TouchEventNotification

class ActionNotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        if (intent.action == TOUCH_PENDING_INTENT_ACTION) {
            TouchEventNotification(this).cancelNotification()
        }
    }
}