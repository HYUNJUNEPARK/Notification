package com.june.notification

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.june.notification.notification.TouchEventNotification
import com.june.notification.notification.twochannelsample.FirstChannelNotification
import com.june.notification.notification.twochannelsample.SecondChannelNotification

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //1ch
    fun notify_1CH_NotificationButtonClicked(v: View) {
        FirstChannelNotification(this).notifyNotification()
    }

    fun notifyUnCancelable_1CH_NotificationButtonClicked(v: View) {
        FirstChannelNotification(this).notifyUnCancelableNotification()
    }

    fun cancel_1CH_NotificationButtonClicked(v: View) {
        FirstChannelNotification(this).cancelNotification()
    }

    //2ch
    fun notify_2CH_NotificationButtonClicked(v: View) {
        SecondChannelNotification(this).notifyNotification()
    }

    fun notifyUnCancelable_2CH_NotificationButtonClicked(v: View) {
        SecondChannelNotification(this).notifyUnCancelableNotification()
    }

    fun cancel_2CH_NotificationButtonClicked(v: View) {
        SecondChannelNotification(this).cancelNotification()
    }

    //touch event
    fun touchEventNotificationButtonClicked(v: View) {
        TouchEventNotification(this).notifyNotification()
    }
}