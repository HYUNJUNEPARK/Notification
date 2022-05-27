package com.june.notification.notification.remoteinput

import android.content.Context
import com.june.notification.notification.Constants.Companion.REMOTE_INPUT_NOTIFICATION_PREF_NAME
import com.june.notification.notification.Constants.Companion.REMOTE_USER_INPUT

class UserInputPreferences(val context: Context) {
    fun userInputPref(userInput: String) {
        val prefs = context.getSharedPreferences(REMOTE_INPUT_NOTIFICATION_PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().run {
            putString(REMOTE_USER_INPUT, userInput)
            commit()
        }
    }

    fun userInput(): String {
        val prefs = context.getSharedPreferences(REMOTE_INPUT_NOTIFICATION_PREF_NAME, Context.MODE_PRIVATE)
        val userInput = prefs.getString(REMOTE_USER_INPUT, "null")
        return userInput!!
    }
}