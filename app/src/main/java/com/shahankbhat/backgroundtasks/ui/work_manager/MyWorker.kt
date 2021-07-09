package com.shahankbhat.backgroundtasks.ui.work_manager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.shahankbhat.backgroundtasks.util.sendMessages


class MyWorker(val context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        Thread.sleep(1000)

        applicationContext.sendMessages(hashCode().toString())

        return Result.success()
    }
}