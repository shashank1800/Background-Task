package com.shahankbhat.backgroundtasks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.shahankbhat.backgroundtasks.ui.bound_service.ActivityBoundService
import com.shahankbhat.backgroundtasks.ui.forground_service.ActivityForegroundService
import com.shahankbhat.backgroundtasks.ui.intet_service.ActivityIntentService
import com.shahankbhat.backgroundtasks.ui.job_intent_service.ActivityJobIntentService
import com.shahankbhat.backgroundtasks.ui.local_broadcast_manager.ActivityA
import com.shahankbhat.backgroundtasks.ui.work_manager.ActivityWorkManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_local_broadcast_manager).setOnClickListener {
            startActivity(Intent(applicationContext, ActivityA::class.java))
        }

        findViewById<Button>(R.id.btn_intent_service).setOnClickListener {
            startActivity(Intent(applicationContext, ActivityIntentService::class.java))
        }

        findViewById<Button>(R.id.btn_job_intent_service).setOnClickListener {
            startActivity(Intent(applicationContext, ActivityJobIntentService::class.java))
        }

        findViewById<Button>(R.id.btn_bound_service).setOnClickListener {
            startActivity(Intent(applicationContext, ActivityBoundService::class.java))
        }

        findViewById<Button>(R.id.btn_work_manager).setOnClickListener {
            startActivity(Intent(applicationContext, ActivityWorkManager::class.java))
        }

        findViewById<Button>(R.id.btn_foreground_service).setOnClickListener {
            startActivity(Intent(applicationContext, ActivityForegroundService::class.java))
        }

    }
}