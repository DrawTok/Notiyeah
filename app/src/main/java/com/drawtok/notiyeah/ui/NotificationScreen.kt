package com.drawtok.notiyeah.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.drawtok.notiyeah.data.NotificationEntity

@Composable
fun NotificationScreen() {
    val notifications: List<NotificationEntity> = List(10) { index ->
        NotificationEntity(index, "Title $index", "Content $index", "App $index", System.currentTimeMillis())
    }
    LazyColumn {
        items(notifications, key = { it.id }) { notification ->
            NotificationItem(notification)
        }
    }

}