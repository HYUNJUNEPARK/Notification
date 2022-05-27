package com.june.notification.notification

class Constants {
    companion object {
        //Standard
        const val CHANNEL_ID = "channel"
        const val CHANNEL_NAME = "My channel name"
        const val CHANNEL_DESCRIPTION = "My channel description"
        const val NOTIFICATION_TITLE = "notification title"
        const val NOTIFICATION_CONTENT = "notification content"
        const val NOTIFICATION_ID = 0

        //TouchEvent notification
        const val TOUCH_CHANNEL_ID = "touch-channel"
        const val TOUCH_CHANNEL_NAME = "My touch channel name"
        const val TOUCH_CHANNEL_DESCRIPTION = "My touch channel description"
        const val TOUCH_NOTIFICATION_TITLE = "touch notification title"
        const val TOUCH_NOTIFICATION_CONTENT = "touch notification content"
        const val TOUCH_NOTIFICATION_ID = 1
        const val TOUCH_PENDING_INTENT_REQUEST_CODE = 2
        const val TOUCH_PENDING_INTENT_ACTION = "touch event notification"

        //Action notification
        const val ACTION_CHANNEL_ID = "action-channel"
        const val ACTION_CHANNEL_NAME = "My action channel name"
        const val ACTION_CHANNEL_DESCRIPTION = "My action channel description"
        const val ACTION_NOTIFICATION_TITLE = "action notification title"
        const val ACTION_NOTIFICATION_CONTENT = "action notification content"
        const val ACTION_NOTIFICATION_ID = 3
        const val ACTION_PENDING_INTENT_REQUEST_CODE = 4
        const val ACTION_PENDING_INTENT_ACTION = "action event notification"
        const val ACTION_NOTIFICATION_BUTTON_TITLE = "Action"

        //Remote input notification
        const val REMOTE_INPUT_CHANNEL_ID = "remote-input-channel"
        const val REMOTE_INPUT_CHANNEL_NAME = "My remote input channel name"
        const val REMOTE_INPUT_CHANNEL_DESCRIPTION = "My remote input channel description"
        const val REMOTE_INPUT_NOTIFICATION_TITLE = "remote input notification title"
        const val REMOTE_INPUT_NOTIFICATION_CONTENT = "remote input notification content"
        const val REMOTE_INPUT_NOTIFICATION_ID = 5
        const val REMOTE_INPUT_PENDING_INTENT_REQUEST_CODE = 6
        const val REMOTE_INPUT_PENDING_INTENT_ACTION = "remote input event notification"
        const val REMOTE_INPUT_KEY_TEXT_REPLY = "key_text_reply"
        const val REMOTE_INPUT_BUTTON_TITLE = "답장"

        const val REMOTE_INPUT_NOTIFICATION_REPLY_TITLE = "remote input arrived successfully"
        const val REMOTE_INPUT_NOTIFICATION_REPLY_CONTENT = "user input arrived to receiver "
    }
}