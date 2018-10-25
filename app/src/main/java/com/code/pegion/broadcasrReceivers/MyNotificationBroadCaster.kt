package com.code.pegion.broadcasrReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyNotificationBroadCaster : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent == null || context == null) return

        val title = intent.getStringExtra("notifiable")

    }
}