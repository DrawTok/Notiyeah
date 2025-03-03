package com.drawtok.notiyeah

import android.Manifest
import android.app.AlertDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.drawtok.notiyeah.service.ForegroundNotificationService
import com.drawtok.notiyeah.service.NotificationService
import com.drawtok.notiyeah.ui.NotificationScreen
import com.drawtok.notiyeah.viewmodel.NotificationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startForegroundService()

        if (!isNotificationListenerEnabled(this)) {
            requestNotificationPermission()
        }

        requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)

        setContent {
            val viewModel: NotificationViewModel = viewModel()
            NotificationScreen(viewModel)
        }
    }

    private fun startForegroundService() {
        val serviceIntent = Intent(this, ForegroundNotificationService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }
    }

    private fun requestNotificationPermission() {
        AlertDialog.Builder(this)
            .setTitle("Cấp quyền đọc thông báo")
            .setMessage("Ứng dụng cần quyền để đọc thông báo, vui lòng cấp quyền trong cài đặt.")
            .setPositiveButton("Mở Cài Đặt") { _, _ ->
                startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
            }
            .setNegativeButton("Hủy", null)
            .show()
    }

    private fun isNotificationListenerEnabled(context: Context): Boolean {
        val cn = ComponentName(context, NotificationService::class.java)
        val enabledListeners = Settings.Secure.getString(context.contentResolver, "enabled_notification_listeners")
        Log.d("kkgkg", "cn: ${cn.flattenToString()}")
        Log.d("kkgkg", "isNotificationListenerEnabled: $enabledListeners")
        return enabledListeners?.contains(cn.flattenToString()) == true
    }

    private fun restartNotificationService() {
        stopService(Intent(this, NotificationService::class.java))
        startService(Intent(this, NotificationService::class.java))
    }

    override fun onResume() {
        super.onResume()
        if (isNotificationListenerEnabled(this)) {
            restartNotificationService()
        }
    }
}
