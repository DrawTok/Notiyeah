package com.drawtok.notiyeah.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {

    @Insert
    suspend fun insert(notification: NotificationEntity)

    @Query("SELECT * FROM notifications ORDER BY timestamp DESC")
    fun getAllNotifications(): Flow<List<NotificationEntity>>

    @Transaction
    @Query("SELECT * FROM package WHERE package_name = :packageName")
    fun getAllNotificationsByPkgName(packageName: String): Flow<PackageWithNotifications?>

}