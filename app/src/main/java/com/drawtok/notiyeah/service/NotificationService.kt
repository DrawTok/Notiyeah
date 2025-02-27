package com.drawtok.notiyeah.service

import android.app.Notification
import android.graphics.Bitmap
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.drawtok.notiyeah.data.NotificationEntity

class NotificationService : NotificationListenerService() {

    private val seenNotifications = HashSet<String>()

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

    }

    fun extractNotificationImage(notification: Notification): Bitmap? {
        val extras = notification.extras
        val image = extras.getParcelable<Bitmap>(Notification.EXTRA_PICTURE)
        return image
    }

    private fun splitText(input: String): Triple<String, String, String> {
        val parts = input.split(",").map { it.trim() }
        val first = parts.getOrNull(0) ?: ""
        val second = parts.getOrNull(1) ?: ""
        val remaining = parts.drop(2).joinToString(", ")

        return Triple(first, second, remaining)
    }
}
