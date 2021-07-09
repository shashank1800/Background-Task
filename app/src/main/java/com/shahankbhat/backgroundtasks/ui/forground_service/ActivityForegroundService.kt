package com.shahankbhat.backgroundtasks.ui.forground_service

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import androidx.databinding.DataBindingUtil
import com.shahankbhat.backgroundtasks.R
import com.shahankbhat.backgroundtasks.databinding.ActivityForegroundServiceBinding
import com.shahankbhat.backgroundtasks.util.regLocalBroadcastManager
import com.shahankbhat.backgroundtasks.util.unRegLocalBroadcastManager
import java.text.SimpleDateFormat
import java.util.*

class ActivityForegroundService : AppCompatActivity() {

    lateinit var binding: ActivityForegroundServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_foreground_service)

        applicationContext.regLocalBroadcastManager(
            mMessageReceiver
        )

        binding.logBoard.setOnClickListener {
            MyForegroundService.startPretendDownload(this, 5, 1)

            val myForegroundService = Intent(this, MyForegroundService::class.java)
            bindService(
                myForegroundService,
                serviceConnection,
                Context.BIND_AUTO_CREATE
            )
        }
    }


    private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent) {
            val message = intent.extras?.getString("message")
            val logMessage = getLogTimeStamp()
            binding.logBoard.append("$logMessage - $message\n")
        }

    }

    fun getLogTimeStamp(): String{
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return simpleDateFormat.format(Date(System.currentTimeMillis()))
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        applicationContext.unRegLocalBroadcastManager(
            mMessageReceiver
        )
    }
}