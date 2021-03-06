package com.shahankbhat.backgroundtasks.ui.bound_service

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import androidx.databinding.DataBindingUtil
import com.shahankbhat.backgroundtasks.R
import com.shahankbhat.backgroundtasks.databinding.ActivityBoundServiceBinding
import com.shahankbhat.backgroundtasks.ui.intet_service.MyIntentService
import com.shahankbhat.backgroundtasks.util.regLocalBroadcastManager
import com.shahankbhat.backgroundtasks.util.unRegLocalBroadcastManager
import java.text.SimpleDateFormat
import java.util.*

class ActivityBoundService : AppCompatActivity() {

    lateinit var binding: ActivityBoundServiceBinding

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bound_service)
        applicationContext.regLocalBroadcastManager(mMessageReceiver)

        val myBoundService = Intent(applicationContext, MyBoundService::class.java)

        binding.btnStartService.setOnClickListener {
            startService(myBoundService)
        }

        binding.btnBindService.setOnClickListener {
            bindService(
                myBoundService,
                serviceConnection,
                Context.BIND_AUTO_CREATE
            )
        }

        binding.btnUnbindService.setOnClickListener {
            unbindService(serviceConnection)
        }

        binding.btnStopService.setOnClickListener {
            stopService(myBoundService)
        }

        binding.btnCancelBoundService2.setOnClickListener {
            unbindService(serviceConnection)
        }

        binding.btnCallBoundService1.setOnClickListener {
            bindService(
                myBoundService,
                serviceConnection,
                Context.BIND_AUTO_CREATE
            )
        }

        binding.btnCancelBoundService2.setOnClickListener {
            unbindService(serviceConnection)
        }

        binding.btnClear.setOnClickListener {
            binding.logBoard.text = ""
        }
    }

    lateinit var myBoundService: MyBoundService
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            myBoundService =
                (service as MyBoundService.MyBinder).getService()

        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        applicationContext.unRegLocalBroadcastManager(mMessageReceiver)
    }
}