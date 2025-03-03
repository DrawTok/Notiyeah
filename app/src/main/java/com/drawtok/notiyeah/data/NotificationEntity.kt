package com.drawtok.notiyeah.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.drawtok.notiyeah.PackageEntity

@Entity(
    tableName = "notifications", foreignKeys = [
        ForeignKey(
            entity = PackageEntity::class,
            parentColumns = ["package_name"],
            childColumns = ["package_name"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["package_name"])]
)
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "package_name")
    val packageName: String,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long
)
