package com.june.notification.notification.action

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.june.notification.notification.Constants.Companion.ACTION_PENDING_INTENT_ACTION

class ActionNotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ACTION_PENDING_INTENT_ACTION) {
            ActionNotification(context).cancelNotification()
        }
    }
}