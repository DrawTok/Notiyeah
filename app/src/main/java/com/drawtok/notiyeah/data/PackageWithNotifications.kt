package com.drawtok.notiyeah.data

import androidx.room.Embedded
import androidx.room.Relation
import com.drawtok.notiyeah.PackageEntity

data class PackageWithNotifications(
    @Embedded val packageEntity: PackageEntity,
    @Relation(parentColumn = "package_name", entityColumn = "package_name")
    val notifications: List<NotificationEntity>
)