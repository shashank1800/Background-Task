package com.shahankbhat.backgroundtasks.ui.work_manager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.shahankbhat.backgroundtasks.R
import com.shahankbhat.backgroundtasks.databinding.ActivityWorkManagerBinding
import com.shahankbhat.backgroundtasks.ui.job_intent_service.JOB_INTENT_SERVICE_ACTION
import com.shahankbhat.backgroundtasks.util.regLocalBroadcastManager
import com.shahankbhat.backgroundtasks.util.unRegLocalBroadcastManager
import java.text.SimpleDateFormat
import java.util.*

class ActivityWorkManager : AppCompatActivity() {

    private lateinit var binding: ActivityWorkManagerBinding
    private var workManager =  WorkManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_work_manager)

        applicationContext.regLocalBroadcastManager(
            JOB_INTENT_SERVICE_ACTION,
            mMessageReceiver
        )

        binding.btnWorkNow.setOnClickListener {
            val worker = OneTimeWorkRequest.from(MyWorker::class.java)
            workManager.enqueue(worker)
        }

    }

    private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent) {
            val hashCode = intent.extras?.getString("hashCode")
            val jobCount = intent.extras?.getInt("jobCount", -1)
            val logMessage = getLogTimeStamp()
            binding.logBoard.append("$logMessage - hashcode : $hashCode Job number = $jobCount\n")
        }

    }

    fun getLogTimeStamp(): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return simpleDateFormat.format(Date(System.currentTimeMillis()))
    }

    override fun onDestroy() {
        super.onDestroy()
        applicationContext.unRegLocalBroadcastManager(
            mMessageReceiver
        )
    }
}