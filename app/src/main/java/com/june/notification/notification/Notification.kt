package com.june.notification.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import com.june.notification.notification.Constants.Companion.CHANNEL_DESCRIPTION
import com.june.notification.notification.Constants.Companion.CHANNEL_ID
import com.june.notification.notification.Constants.Companion.CHANNEL_NAME
import com.june.notification.notification.Constants.Companion.NOTIFICATION_CONTENT
import com.june.notification.notification.Constants.Companion.NOTIFICATION_ID
import com.june.notification.notification.Constants.Companion.NOTIFICATION_TITLE

class Notification(private val context: Context) {
    private val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    lateinit var builder: NotificationCompat.Builder

    //TODO 액티비티에서 해당 함수만 호출하면 알림 기능 구현
    fun notifyNotification() {
        notification()
        notificationManager.notify(
            NOTIFICATION_ID,
            builder.build()
        )
    }

    fun notifyUnCancelableNotification() {
        unCancelableNotification()
        notificationManager.notify(
            NOTIFICATION_ID,
            builder.build()
        )
    }

    fun cancelNotification() {
        notificationManager.cancel(NOTIFICATION_ID)
    }

    private fun unCancelableNotification()  {
        builder = notificationBuilder().apply {
            setSmallIcon(android.R.drawable.ic_notification_clear_all)
            setWhen(System.currentTimeMillis())
            setContentTitle(NOTIFICATION_TITLE)
            setContentText(NOTIFICATION_CONTENT)

            //User can not cancel notification
            setAutoCancel(false) //block touch cancel
            setOngoing(true) //block swipe cancel
        }
    }

    private fun notificationBuilder(): NotificationCompat.Builder {
        //O 버전 이상 채널 생성
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )

            //설정 화면에서 채널을 설명
            channel.description = CHANNEL_DESCRIPTION
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
            builder = NotificationCompat.Builder(context, CHANNEL_ID)
            return builder
        }
        //O 버전 미만
        else {
            builder = NotificationCompat.Builder(context)
            return builder
        }
    }

    private fun notification() {
        builder = notificationBuilder().apply {
            setSmallIcon(android.R.drawable.ic_notification_overlay)
            setWhen(System.currentTimeMillis())
            setContentTitle(NOTIFICATION_TITLE)
            setContentText(NOTIFICATION_CONTENT)
        }
    }
}