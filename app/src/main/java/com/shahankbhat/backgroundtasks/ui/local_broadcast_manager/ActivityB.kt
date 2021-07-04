package com.shahankbhat.backgroundtasks.ui.local_broadcast_manager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.shahankbhat.backgroundtasks.R
import com.shahankbhat.backgroundtasks.ui.local_broadcast_manager.ActivityA.Companion.CUSTOM_ACTION


class ActivityB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        findViewById<Button>(R.id.btn_send).setOnClickListener {

            val intent = Intent(CUSTOM_ACTION)
            intent.putExtra("message", findViewById<EditText>(R.id.et_message).text.toString())
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)

            finish()
        }
    }
}