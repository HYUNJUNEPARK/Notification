package com.june.notification.notification.remoteinput

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
import androidx.core.app.RemoteInput
import com.june.notification.R
import com.june.notification.activity.RemoteInputActivity
import com.june.notification.notification.Constants
import com.june.notification.notification.Constants.Companion.REMOTE_INPUT_CHANNEL_DESCRIPTION
import com.june.notification.notification.Constants.Companion.REMOTE_INPUT_CHANNEL_ID
import com.june.notification.notification.Constants.Companion.REMOTE_INPUT_CHANNEL_NAME
import com.june.notification.notification.Constants.Companion.REMOTE_INPUT_KEY_TEXT_REPLY
import com.june.notification.notification.Constants.Companion.REMOTE_INPUT_NOTIFICATION_CONTENT
import com.june.notification.notification.Constants.Companion.REMOTE_INPUT_NOTIFICATION_ID
import com.june.notification.notification.Constants.Companion.REMOTE_INPUT_NOTIFICATION_TITLE
import com.june.notification.notification.Constants.Companion.REMOTE_INPUT_PENDING_INTENT_ACTION
import com.june.notification.notification.Constants.Companion.REMOTE_INPUT_PENDING_INTENT_REQUEST_CODE
import com.june.notification.notification.Constants.Companion.REMOTE_INPUT_BUTTON_TITLE
import com.june.notification.notification.Constants.Companion.REMOTE_INPUT_NOTIFICATION_REPLY_CONTENT
import com.june.notification.notification.Constants.Companion.REMOTE_INPUT_NOTIFICATION_REPLY_TITLE

class RemoteInputNotification (private val context: Context) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    lateinit var builder: NotificationCompat.Builder

    //TODO User Input 을 받을 수 있는 알림을 띄울 때 사용
    fun notifyNotification() {
        notification()
        notificationManager.notify(
            REMOTE_INPUT_NOTIFICATION_ID,
            builder.build()
        )
    }

    //TODO 브로드캐스트 리시버에서 input 을 성공적으로 받았음을 알려줄 때 사용
    //브로드캐스트 리시버에서 사용자의 입력 글을 받은 후 알림을 갱신해 줘야하며 RemoteInput 의 알림을 띄울 때 사용했던 알림 객체의 식별값(REMOTE_INPUT_NOTIFICATION_ID)을 지정함
    fun notifyReplyNotification() {
        replyNotification()
        notificationManager.notify(
            REMOTE_INPUT_NOTIFICATION_ID,
            builder.build()
        )
    }

    fun cancelNotification() {
        notificationManager.cancel(REMOTE_INPUT_NOTIFICATION_ID)
    }

    private fun notificationBuilder(): NotificationCompat.Builder {
        //O 버전 이상 채널 생성
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                REMOTE_INPUT_CHANNEL_ID,
                REMOTE_INPUT_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )

            //설정 화면에서 채널을 설명
            channel.description = REMOTE_INPUT_CHANNEL_DESCRIPTION
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
            builder = NotificationCompat.Builder(context, Constants.REMOTE_INPUT_CHANNEL_ID)
            return builder
        }
        //O 버전 미만
        else {
            builder = NotificationCompat.Builder(context)
            return builder
        }
    }

    private fun notification() {
        val intent = Intent(context, RemoteInputNotificationReceiver::class.java)
        intent.action = REMOTE_INPUT_PENDING_INTENT_ACTION
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            REMOTE_INPUT_PENDING_INTENT_REQUEST_CODE,
            intent,
            //PendingIntents attached to actions with remote inputs must be mutable
            PendingIntent.FLAG_MUTABLE
        )
        builder = notificationBuilder().apply {
            setSmallIcon(android.R.drawable.ic_notification_overlay)
            setWhen(System.currentTimeMillis())
            setContentTitle(REMOTE_INPUT_NOTIFICATION_TITLE)
            setContentText(REMOTE_INPUT_NOTIFICATION_CONTENT)

            //remote input notification
            var remoteInput: RemoteInput = RemoteInput.Builder(REMOTE_INPUT_KEY_TEXT_REPLY).run {
                setLabel(REMOTE_INPUT_BUTTON_TITLE)
                build()
            }
            addAction(
                NotificationCompat.Action.Builder(
                    R.drawable.ic_baseline_send_24,
                    REMOTE_INPUT_BUTTON_TITLE,
                    pendingIntent
                ).addRemoteInput(remoteInput)
                 .build()
            )
        }
    }

    private fun replyNotification() {
        val intent = Intent(context, RemoteInputActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            REMOTE_INPUT_PENDING_INTENT_REQUEST_CODE,
            intent,
            //Targeting S+ (version 31 and above) requires that one of FLAG_IMMUTABLE or FLAG_MUTABLE be specified when creating a PendingIntent.
            PendingIntent.FLAG_IMMUTABLE
        )

        builder = notificationBuilder().apply {
            setSmallIcon(android.R.drawable.ic_notification_overlay)
            setWhen(System.currentTimeMillis())
            setContentTitle(REMOTE_INPUT_NOTIFICATION_REPLY_TITLE)
            setContentText(REMOTE_INPUT_NOTIFICATION_REPLY_CONTENT)

            //touch event
            setContentIntent(pendingIntent)
        }
    }
}