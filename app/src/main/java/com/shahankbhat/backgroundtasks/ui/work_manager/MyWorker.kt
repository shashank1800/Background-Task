package com.shahankbhat.backgroundtasks.ui.work_manager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.shahankbhat.backgroundtasks.util.sendBroadcastMessage

const val WORK_MANAGER_ACTION =
    "com.shahankbhat.backgroundtasks.JOB_INTENT_SERVICE_ACTION"


class MyWorker(val context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        Thread.sleep(1000)

        applicationContext.sendBroadcastMessage(WORK_MANAGER_ACTION, hashCode().toString())

        return Result.success()
    }
}