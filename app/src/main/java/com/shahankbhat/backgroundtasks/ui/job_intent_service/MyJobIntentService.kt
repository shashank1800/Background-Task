package com.shahankbhat.backgroundtasks.ui.job_intent_service

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import androidx.localbroadcastmanager.content.LocalBroadcastManager

const val JOB_INTENT_SERVICE_ACTION = "com.shahankbhat.backgroundtasks.JOB_INTENT_SERVICE_ACTION"

const val EXTRA_PARAM1 =
    "com.shahankbhat.backgroundtasks.ui.job_intent_service.extra.PARAM1"
const val EXTRA_PARAM2 =
    "com.shahankbhat.backgroundtasks.ui.job_intent_service.extra.PARAM2"

class MyJobIntentService : JobIntentService() {

    private fun handleActionFoo(jobCount: Int?, param2: String?) {

        Thread.sleep(2000)

        val intent = Intent(JOB_INTENT_SERVICE_ACTION)
        intent.putExtra("hashCode", this.hashCode().toString())
        intent.putExtra("jobCount", jobCount)

        val localBroadcastManager = LocalBroadcastManager.getInstance(this)
        localBroadcastManager.sendBroadcast(intent)
    }

    companion object {

        private const val JOB_ID = 1
        fun enqueueWork(context: Context, jobCount: Int) {

            val mIntent = Intent(context, MyJobIntentService::class.java).apply {
                action = JOB_INTENT_SERVICE_ACTION
                putExtra(EXTRA_PARAM1, jobCount)
                putExtra(EXTRA_PARAM2, "World")
            }

            enqueueWork(context, MyJobIntentService::class.java, JOB_ID, mIntent)
        }
    }

    override fun onHandleWork(intent: Intent) {

        if (intent.action == JOB_INTENT_SERVICE_ACTION) {
            val param1 = intent.getIntExtra(EXTRA_PARAM1, -1)
            val param2 = intent.getStringExtra(EXTRA_PARAM2)
            handleActionFoo(param1, param2)
        }

    }
}