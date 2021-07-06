package com.shahankbhat.backgroundtasks.ui.intet_service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.shahankbhat.backgroundtasks.R
import java.text.SimpleDateFormat
import java.util.*

class ActivityIntentService : AppCompatActivity() {

    private lateinit var localBroadcastManager: LocalBroadcastManager
    private lateinit var logBoardTextView:TextView

    var jobCount = 0

    private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent) {
            val message = intent.extras?.getString("message")
            val logMessage = getLogTimeStamp()
            logBoardTextView.append("$logMessage - $message\n")
        }

    }

    fun getLogTimeStamp(): String{
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return simpleDateFormat.format(Date(System.currentTimeMillis()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_service)

        localBroadcastManager = LocalBroadcastManager.getInstance(this)

        // Register Local BroadCast Receiver
        localBroadcastManager.registerReceiver(
            mMessageReceiver,
            IntentFilter(ACTION_BC_MANAGER_INTENT_SERVICE)
        )

        logBoardTextView = findViewById(R.id.log_board)

        findViewById<Button>(R.id.btn_intent_service).setOnClickListener {
            MyIntentService.startActionFoo(this, "Hello", "World")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        localBroadcastManager.unregisterReceiver(mMessageReceiver)
    }
}