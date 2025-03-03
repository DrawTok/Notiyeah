package com.drawtok.notiyeah.service

import android.app.Notification
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Icon
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
        val extras = sbn.notification.extras
        val timestamp = sbn.postTime
        val packageName = sbn.packageName
        val title = extras.getString(Notification.EXTRA_TITLE) ?: "No Title"

        val content = extras.getString(Notification.EXTRA_TEXT) ?: "No Content"
        Log.d(
            "NotificationService",
            "Notification posted: $packageName - $title - $content - $timestamp"
        )
        val notification = NotificationEntity(
            title = title,
            content = content,
            packageName = packageName,
            timestamp = timestamp
        )

        CoroutineScope(Dispatchers.IO).launch {
            repository.insertNotification(notification)
        }

    }
}
