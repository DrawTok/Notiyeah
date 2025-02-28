package com.drawtok.notiyeah.data

import com.drawtok.notiyeah.data.local.NotificationDatabase
import javax.inject.Inject

class NotificationRepository @Inject constructor(private val db: NotificationDatabase) {
    suspend fun saveNotification(notification: NotificationEntity) {
        db.notificationDao().insert(notification)
    }

    suspend fun getAllNotifications(): List<NotificationEntity> {
        return db.notificationDao().getAllNotifications()
    }
}