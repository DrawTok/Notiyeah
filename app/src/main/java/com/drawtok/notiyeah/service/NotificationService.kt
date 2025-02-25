package com.drawtok.notiyeah.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationService : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        Log.d(
            "NotificationService",
            "Notification posted: ${sbn.packageName} - ${sbn.notification.tickerText}"
        )
    }
}
