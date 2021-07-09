package com.shahankbhat.backgroundtasks.ui.forground_service

import android.annotation.SuppressLint
import android.app.IntentService
import android.app.Notification
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder
import androidx.core.app.JobIntentService
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.shahankbhat.backgroundtasks.R
import com.shahankbhat.backgroundtasks.application.MyApplication.Companion.NOTIFICATION_CHANNEL_ID
import com.shahankbhat.backgroundtasks.util.ACTION_BC_MANAGER
import com.shahankbhat.backgroundtasks.util.sendMessages


private const val MINUTE = "minute"
private const val DOWNLOAD_ID = "download_id"

class MyForegroundService : IntentService("MyIntentService") {

    private var percentage: Long = 0L
    private var downloadId: Int = -1

    override fun onCreate() {
        super.onCreate()
        applicationContext.sendMessages(
            "onCreate()"
        )
    }

    @SuppressLint("NewApi")
    override fun onStart(intent: Intent?, startId: Int) {
        applicationContext.sendMessages(
            "onStart()"
        )

        val notificationBuilder = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Title")
            .setContentText("Downloading...")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Much longer text that cannot fit one line...")
            ).setPriority(NotificationCompat.PRIORITY_DEFAULT)

        startForeground(1, notificationBuilder.build())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        applicationContext.sendMessages(
            "onStartCommand()"
        )

        return super.onStartCommand(intent, flags, startId)
    }

    private val myBinder = MyBinder()

    override fun onBind(intent: Intent): IBinder {
        applicationContext.sendMessages(
            "onBind()"
        )
        return myBinder
    }

    inner class MyBinder : Binder() {
        fun getService(): MyForegroundService = this@MyForegroundService
    }


    override fun onUnbind(intent: Intent?): Boolean {

        applicationContext.sendMessages(
            "onUnbind()"
        )

        return super.onUnbind(intent)
    }

    override fun onHandleIntent(intent: Intent?) {
        if (intent?.action == ACTION_BC_MANAGER) {
            val minute = intent.getIntExtra(MINUTE, 0)

            percentage = 0
            downloadId = intent.getIntExtra(DOWNLOAD_ID, 0)
            pretendDownload(minute)
        }

        applicationContext.sendMessages(
            "onHandleIntent()"
        )
    }


    override fun onDestroy() {
        super.onDestroy()

        applicationContext.sendMessages(
            "onDestroy()"
        )
    }

    fun getDownloadPercentage(): Long {
        return percentage
    }

    fun getCurrentDownloadId(): Long {
        return percentage
    }

    private fun pretendDownload(minute: Int) {

        val totalMilliSeconds = minute * 1000L * 60
        val intervalSeconds = 1L

        val timer: CountDownTimer =
            object : CountDownTimer(totalMilliSeconds, intervalSeconds * 1000L) {
                override fun onTick(millisUntilFinished: Long) {
                    percentage = millisUntilFinished / totalMilliSeconds * 100
                }

                override fun onFinish() {
                    percentage = 100
                }
            }

        timer.start()
    }

    companion object {
        private const val JOB_ID = 1

        @SuppressLint("NewApi")
        fun startPretendDownload(context: Context, minute: Int, jobId: Int) {

            val mIntent = Intent(context, MyForegroundService::class.java).apply {
                action = ACTION_BC_MANAGER
                putExtra(MINUTE, minute)
                putExtra(DOWNLOAD_ID, jobId)
            }

//            enqueueWork(context, MyForegroundService::class.java, JOB_ID, mIntent)

//            context.startForegroundService(mIntent)
            ContextCompat.startForegroundService(context, mIntent)
        }
    }
}