package com.shahankbhat.backgroundtasks.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager

fun Context.regLocalBroadcastManager(
    action: String,
    mMessageReceiver: BroadcastReceiver
) {
    val localBroadcastManager = LocalBroadcastManager.getInstance(this)
    // Register Local Broadcast Receiver
    localBroadcastManager.registerReceiver(
        mMessageReceiver,
        IntentFilter(action)
    )
}

fun Context.unRegLocalBroadcastManager(mMessageReceiver: BroadcastReceiver) {
    val localBroadcastManager = LocalBroadcastManager.getInstance(this)
    localBroadcastManager.unregisterReceiver(mMessageReceiver)
}

fun Context.sendBroadcastMessage(action: String, hashCode: String){
    val intent = Intent(action)
    intent.putExtra("hashCode", hashCode)

    val localBroadcastManager = LocalBroadcastManager.getInstance(this)
    localBroadcastManager.sendBroadcast(intent)
}

fun Context.sendMessages(action: String, message: String){
    val intent = Intent(action)
    intent.putExtra("message", message)

    val localBroadcastManager = LocalBroadcastManager.getInstance(this)
    localBroadcastManager.sendBroadcast(intent)
}