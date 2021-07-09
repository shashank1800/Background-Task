package com.shahankbhat.backgroundtasks.application

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

class MyApplication: Application(){


    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    @SuppressLint("NewApi")
    private fun createNotificationChannel(){

        val channelName = "Background Service"
        val chan = NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_DEFAULT)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(chan)
    }

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "com.shahankbhat..notificaiton_channel"
    }
}