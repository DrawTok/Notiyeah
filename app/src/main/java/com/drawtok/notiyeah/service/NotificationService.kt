package com.drawtok.notiyeah.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.drawtok.notiyeah.data.NotificationEntity
import com.drawtok.notiyeah.data.NotificationRepository
import com.drawtok.notiyeah.data.local.NotificationDatabase
import com.drawtok.notiyeah.viewmodel.NotificationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NotificationService : NotificationListenerService() {

    @Inject
    lateinit var repository: NotificationRepository

    override fun onNotificationPosted(sbn: StatusBarNotification) {

        if (sbn.packageName == packageName) return
        val extras = sbn.notification.tickerText
        if (extras.isNullOrEmpty()) return
        val (appName, title, content) = splitText(extras.toString())

        Log.d(
            "NotificationService",
            "Notification posted: $appName - $title - $content"
        )
        val notification = NotificationEntity(
            title = title,
            content = content,
            packageName = sbn.packageName,
            appName = appName,
            timestamp = System.currentTimeMillis()
        )

        CoroutineScope(Dispatchers.IO).launch {
            repository.saveNotification(notification)
        }

    }

    private fun splitText(input: String): Triple<String, String, String> {
        val parts = input.split(",").map { it.trim() }
        val first = parts.getOrNull(0) ?: ""
        val second = parts.getOrNull(1) ?: ""
        val remaining = parts.drop(2).joinToString(", ")

        return Triple(first, second, remaining)
    }
}
