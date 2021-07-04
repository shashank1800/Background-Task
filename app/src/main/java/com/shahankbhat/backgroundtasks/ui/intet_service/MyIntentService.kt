package com.shahankbhat.backgroundtasks.ui.intet_service

import android.app.IntentService
import android.content.Intent
import android.content.Context
import androidx.localbroadcastmanager.content.LocalBroadcastManager

// TODO: Rename actions, choose action names that describe tasks that this
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
const val INTENT_SERVICE_ACTION =
    "com.shahankbhat.backgroundtasks.INTENT_SERVICE_ACTION"

// TODO: Rename parameters
private const val EXTRA_PARAM1 = "com.shahankbhat.backgroundtasks.ui.intet_service.extra.PARAM1"
private const val EXTRA_PARAM2 = "com.shahankbhat.backgroundtasks.ui.intet_service.extra.PARAM2"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.

 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.

 */
class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        if (intent?.action == INTENT_SERVICE_ACTION) {
            val param1 = intent.getStringExtra(EXTRA_PARAM1)
            val param2 = intent.getStringExtra(EXTRA_PARAM2)
            handleActionFoo(param1, param2)
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionFoo(param1: String?, param2: String?) {

        Thread.sleep(2000)

        val intent = Intent(INTENT_SERVICE_ACTION)
        intent.putExtra("hashCode", this.hashCode().toString())

        val localBroadcastManager = LocalBroadcastManager.getInstance(this)
        localBroadcastManager.sendBroadcast(intent)
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
                action = INTENT_SERVICE_ACTION
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }
    }
}