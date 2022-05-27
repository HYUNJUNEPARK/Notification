package com.june.notification.notification.remoteinput

import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.june.notification.notification.Constants.Companion.REMOTE_INPUT_KEY_TEXT_REPLY
import com.june.notification.notification.Constants.Companion.REMOTE_INPUT_PENDING_INTENT_ACTION

class RemoteInputNotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == REMOTE_INPUT_PENDING_INTENT_ACTION) {

            val userInput: CharSequence? = RemoteInput.getResultsFromIntent(intent)?.getCharSequence(REMOTE_INPUT_KEY_TEXT_REPLY)
            RemoteInputNotification(context).notifyReplyNotification()


            Log.d("testLog", "onReceive: $userInput")
            //TODO shared preference 에 데이터 저장
        }
   }
}