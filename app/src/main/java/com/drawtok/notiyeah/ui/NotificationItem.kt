package com.drawtok.notiyeah.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.drawtok.notiyeah.data.NotificationEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun NotificationItem(notification: NotificationEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = notification.title,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.padding(bottom = 10.dp))
            Text(text = notification.content)
            Spacer(Modifier.padding(bottom = 6.dp))
            Text(
                text = SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault())
                    .format(Date(notification.timestamp)), fontSize = 12.sp
            )
        }
    }
}