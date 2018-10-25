package com.code.pegion.notificationManager

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.support.v4.app.NotificationCompat
import com.code.pegion.R
import com.code.pegion.model.Notifiable

class MyNotificationManager private constructor(private val application: Application) {
    private var mNotificationChannel: NotificationChannel
    private val mId2NotificationMap = hashMapOf<Int, Notification>()
    private val mNotificationManager = application.getSystemService(Application.NOTIFICATION_SERVICE) as NotificationManager

    init {
        mNotificationChannel = NotificationChannel(CHANNEL_ID, "Immediate Notifications", NotificationManager.IMPORTANCE_HIGH)
        mNotificationManager.createNotificationChannel(mNotificationChannel)
    }

    fun schedule(notifiable: Notifiable) {
        if (mId2NotificationMap.containsKey(notifiable.getId())) return

        val notification = NotificationCompat.Builder(application, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_event)
                .setContentTitle(notifiable.getTitle())
                .setContentText(notifiable.getContent())
                .setAutoCancel(true)
                .setChannelId(CHANNEL_ID)
                .build()
        mNotificationManager.notify(notifiable.getId(), notification)
        mId2NotificationMap[notifiable.getId()] = notification
    }

    fun cancel(notifiable: Notifiable) {
        cancel(notifiable.getId())
    }

    fun cancel(notificationId: Int) {
        if (!mId2NotificationMap.containsKey(notificationId)) return

        mNotificationManager.cancel(notificationId)
        mId2NotificationMap.remove(notificationId)
    }

    companion object {
        private const val CHANNEL_ID = "Channel.Id"
        private var INSTANCE: MyNotificationManager? = null

        fun getInstance(application: Application): MyNotificationManager {
            if (INSTANCE == null)
                INSTANCE = MyNotificationManager(application)

            return INSTANCE!!
        }
    }
}