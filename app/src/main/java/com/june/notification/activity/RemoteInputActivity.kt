package com.june.notification.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.june.notification.databinding.ActivityRemoteInputBinding
import com.june.notification.notification.remoteinput.RemoteInputNotification
import com.june.notification.notification.remoteinput.UserInputPreferences

class RemoteInputActivity : AppCompatActivity() {
    private val binding by lazy { ActivityRemoteInputBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        RemoteInputNotification(this).cancelNotification()
        val userInput = UserInputPreferences(this).userInput()
        binding.userInputTextView.text = userInput
    }
}