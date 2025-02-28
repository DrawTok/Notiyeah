package com.drawtok.notiyeah.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.drawtok.notiyeah.data.NotificationDao
import com.drawtok.notiyeah.data.NotificationEntity

@Database(entities = [NotificationEntity::class], version = 1, exportSchema = true)
abstract class NotificationDatabase : RoomDatabase() {
    abstract  fun notificationDao(): NotificationDao
}