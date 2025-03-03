package com.drawtok.notiyeah.data

import com.drawtok.notiyeah.data.local.NotificationDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationRepository @Inject constructor(private val db: NotificationDatabase) {
    suspend fun insertNotification(notification: NotificationEntity) {
        db.notificationDao().insert(notification)
    }

    fun getAllNotificationsByPkgName(packageName: String): Flow<PackageWithNotifications?> {
        return db.notificationDao().getAllNotificationsByPkgName(packageName)
    }
}