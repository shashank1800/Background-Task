package com.shahankbhat.backgroundtasks.ui.bound_service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.shahankbhat.backgroundtasks.util.ACTION_BC_MANAGER
import com.shahankbhat.backgroundtasks.util.sendMessages


class MyBoundService : Service() {

/*
    - Class used for the client Binder.  Because we know this service always
    runs in the same process as its clients, we don't need to deal with IPC.
    - Return this instance of MyBoundService so clients can call public methods
*/
    private val myBinder = MyBinder()

    override fun onBind(intent: Intent): IBinder {
        applicationContext.sendMessages("onBind()")
        return myBinder
    }

    inner class MyBinder : Binder() {
        fun getService(): MyBoundService = this@MyBoundService
    }

    fun getServicePublicMethod(): String{
        return "Service public method"
    }

    override fun onCreate() {
        super.onCreate()
        applicationContext.sendMessages("onCreate()")
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)

        applicationContext.sendMessages("onStart()")

        if (intent?.action == ACTION_BC_MANAGER) {
            handleActionFoo()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        applicationContext.sendMessages("onStartCommand()")
        return super.onStartCommand(intent, flags, startId)
    }


    override fun onUnbind(intent: Intent?): Boolean {
        applicationContext.sendMessages("onUnbind()")
        return true
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        applicationContext.sendMessages("onRebind()")
    }

    override fun onDestroy() {
        super.onDestroy()
        applicationContext.sendMessages("onDestroy()")
    }


    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionFoo() {
        Thread.sleep(2000)
        // Do work here
    }

}