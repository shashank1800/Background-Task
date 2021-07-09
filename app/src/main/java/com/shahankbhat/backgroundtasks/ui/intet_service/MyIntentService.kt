package com.shahankbhat.backgroundtasks.ui.intet_service

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.os.Binder
import android.os.IBinder
import com.shahankbhat.backgroundtasks.util.ACTION_BC_MANAGER
import com.shahankbhat.backgroundtasks.util.sendMessages

private const val EXTRA_PARAM1 = "com.shahankbhat.backgroundtasks.ui.intet_service.extra.PARAM1"
private const val EXTRA_PARAM2 = "com.shahankbhat.backgroundtasks.ui.intet_service.extra.PARAM2"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.

 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.

 */
class MyIntentService : IntentService("MyIntentService") {

    override fun onCreate() {
        super.onCreate()
        applicationContext.sendMessages( "onCreate()")
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)

        applicationContext.sendMessages("onStart()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        applicationContext.sendMessages("onStartCommand()")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onHandleIntent(intent: Intent?) {
        if (intent?.action == ACTION_BC_MANAGER) {
            val param1 = intent.getStringExtra(EXTRA_PARAM1)
            val param2 = intent.getStringExtra(EXTRA_PARAM2)
            handleActionFoo(param1, param2)
        }

        applicationContext.sendMessages("onHandleIntent()")
    }

    private val myBinder = MyBinder()

    override fun onBind(intent: Intent?): IBinder? {
        applicationContext.sendMessages("onBind()")
        return myBinder
    }

    inner class MyBinder : Binder(){
        fun getService(): MyIntentService = this@MyIntentService
    }


    override fun onUnbind(intent: Intent?): Boolean {

        applicationContext.sendMessages("onUnbind()")

        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()

        applicationContext.sendMessages("onDestroy()")
    }


    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionFoo(param1: String?, param2: String?) {
        Thread.sleep(2000)
        // Do work here
    }

    companion object {
        /**
         * Starts this service to perform action Foo with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun startActionFoo(context: Context, param1: String, param2: String) {
            val intent = Intent(context, MyIntentService::class.java).apply {
                action = ACTION_BC_MANAGER
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }
    }
}