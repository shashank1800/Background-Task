package com.shahankbhat.backgroundtasks.ui.local_broadcast_manager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.shahankbhat.backgroundtasks.R
import com.shahankbhat.backgroundtasks.util.regLocalBroadcastManager
import com.shahankbhat.backgroundtasks.util.unRegLocalBroadcastManager


class ActivityA : AppCompatActivity() {

    private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {

            val message = intent.extras?.getString("message")

            Log.e("message", message.toString())

            findViewById<TextView>(R.id.message_board).text = message

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)

        applicationContext.regLocalBroadcastManager(mMessageReceiver)

        findViewById<Button>(R.id.btn_goto_activity_b).setOnClickListener {
            startActivity(Intent(applicationContext, ActivityB::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        applicationContext.unRegLocalBroadcastManager(
            mMessageReceiver
        )
    }
}