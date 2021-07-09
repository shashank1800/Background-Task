package com.shahankbhat.backgroundtasks.ui.local_broadcast_manager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.shahankbhat.backgroundtasks.R
import com.shahankbhat.backgroundtasks.util.ACTION_BC_MANAGER
import com.shahankbhat.backgroundtasks.util.sendMessages

class ActivityB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        findViewById<Button>(R.id.btn_send).setOnClickListener {

            applicationContext.sendMessages(
                "message : ${findViewById<EditText>(R.id.et_message).text}"
            )

            finish()
        }
    }
}