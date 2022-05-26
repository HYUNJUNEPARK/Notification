package com.june.notification

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.june.notification.notification.Notification
import com.june.notification.notification.TouchEventNotification
import com.june.notification.notification.actionnotification.ActionNotification

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //standard
    fun notifyNotificationButtonClicked(v: View) {
        Notification(this).notifyNotification()
    }

    fun notifyUnCancelableNotificationButtonClicked(v: View) {
        Notification(this).notifyUnCancelableNotification()
    }

    fun cancelNotificationButtonClicked(v: View) {
        Notification(this).cancelNotification()
    }

    //touch event notification
    fun touchEventNotificationButtonClicked(v: View) {
        TouchEventNotification(this).notifyNotification()
    }

    //action notification
    fun actionNotificationButtonClicked(v: View) {
        ActionNotification(this).notifyNotification()
    }


}