package com.june.notification.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import com.june.notification.activity.ActionNotificationActivity
import com.june.notification.notification.Constants.Companion.TOUCH_PENDING_INTENT_ACTION
import com.june.notification.notification.Constants.Companion.TOUCH_PENDING_INTENT_REQUEST_CODE
import com.june.notification.notification.Constants.Companion.TOUCH_CHANNEL_DESCRIPTION
import com.june.notification.notification.Constants.Companion.TOUCH_CHANNEL_ID
import com.june.notification.notification.Constants.Companion.TOUCH_CHANNEL_NAME
import com.june.notification.notification.Constants.Companion.TOUCH_NOTIFICATION_CONTENT
import com.june.notification.notification.Constants.Companion.TOUCH_NOTIFICATION_ID
import com.june.notification.notification.Constants.Companion.TOUCH_NOTIFICATION_TITLE

class TouchEventNotification(private val context: Context) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    lateinit var builder: NotificationCompat.Builder

    fun notifyNotification() {
        notification()
        notificationManager.notify(
            TOUCH_NOTIFICATION_ID,
            builder.build()
        )
    }

    fun cancelNotification() {
        notificationManager.cancel(TOUCH_NOTIFICATION_ID)
    }


    private fun notificationBuilder(): NotificationCompat.Builder {
        //O 버전 이상 채널 생성
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                TOUCH_CHANNEL_ID,
                TOUCH_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )

            //설정 화면에서 채널을 설명
            channel.description = TOUCH_CHANNEL_DESCRIPTION
            //홈 화면 앱에 배지 아이콘 표시
            channel.setShowBadge(true)
            //진동 설정 및 패턴
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100, 200, 100, 200)
            //불빛 표시 및 색상
            channel.enableLights(true)
            channel.lightColor = Color.RED
            //알림음
            val ringtoneUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()
            channel.setSound(ringtoneUri, audioAttributes)
            //채널을 NotificationManager 에 등록
            notificationManager.createNotificationChannel(channel)
            //채널을 이용해 빌더 생성
            builder = NotificationCompat.Builder(context, TOUCH_CHANNEL_ID)
            return builder
        }
        //O 버전 미만
        else {
            builder = NotificationCompat.Builder(context)
            return builder
        }
    }

    private fun notification() {
        val intent = Intent(context, ActionNotificationActivity::class.java)
        intent.action = TOUCH_PENDING_INTENT_ACTION
        val pendingIntent = PendingIntent.getActivity(
            context,
            TOUCH_PENDING_INTENT_REQUEST_CODE,
            intent,
            //Targeting S+ (version 31 and above) requires that one of FLAG_IMMUTABLE or FLAG_MUTABLE be specified when creating a PendingIntent.
            PendingIntent.FLAG_IMMUTABLE
        )

        builder = notificationBuilder().apply {
            setSmallIcon(android.R.drawable.ic_notification_overlay)
            setWhen(System.currentTimeMillis())
            setContentTitle(TOUCH_NOTIFICATION_TITLE)
            setContentText(TOUCH_NOTIFICATION_CONTENT)

            //touch event
            setContentIntent(pendingIntent)
        }
    }
}