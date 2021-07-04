package com.shahankbhat.backgroundtasks.ui.job_intent_service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.shahankbhat.backgroundtasks.R
import com.shahankbhat.backgroundtasks.databinding.ActivityJobIntentServiceBinding
import com.shahankbhat.backgroundtasks.util.regLocalBroadcastManager
import com.shahankbhat.backgroundtasks.util.unRegLocalBroadcastManager
import java.text.SimpleDateFormat
import java.util.*

class ActivityJobIntentService : AppCompatActivity() {

    var jobCount = 0
    lateinit var binding: ActivityJobIntentServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_job_intent_service)

        applicationContext.regLocalBroadcastManager(
            JOB_INTENT_SERVICE_ACTION,
            mMessageReceiver
        )

        binding.btnJobIntentService.setOnClickListener {
            binding.logBoard.append("Job $jobCount enqueued\n")

            MyJobIntentService.enqueueWork(this, jobCount)
            jobCount += 1
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