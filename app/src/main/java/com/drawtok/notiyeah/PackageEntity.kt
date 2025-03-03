package com.drawtok.notiyeah

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "package")
class PackageEntity(
    @PrimaryKey
    @ColumnInfo(name = "package_name")
    val packageName: String,
)