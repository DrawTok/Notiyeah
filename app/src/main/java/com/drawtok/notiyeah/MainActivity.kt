package com.drawtok.notiyeah

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import com.drawtok.notiyeah.service.NotificationService

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!isNotificationListenerEnabled(this)) {
            requestNotificationPermission( this)
        }

        val filter = IntentFilter("com.drawtok.notiyeah.NOTIFICATION_RECEIVED")
        registerReceiver(notificationReceiver, filter, Context.RECEIVER_NOT_EXPORTED)

    }

    private val notificationReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val packageName = intent?.getStringExtra("packageName")
            val tickerText = intent?.getStringExtra("tickerText")
            Log.d("MainActivity", "📩 Nhận broadcast: $packageName - $tickerText")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(notificationReceiver)
    }

    private fun requestNotificationPermission(context: Context) {
        val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
        context.startActivity(intent)
    }

    private fun isNotificationListenerEnabled(context: Context): Boolean {
        val cn = ComponentName(context, NotificationService::class.java)
        val enabledListeners = Settings.Secure.getString(context.contentResolver, "enabled_notification_listeners")
        return enabledListeners?.contains(cn.flattenToString()) == true
    }

}

