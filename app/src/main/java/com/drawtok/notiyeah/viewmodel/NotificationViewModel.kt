package com.drawtok.notiyeah.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drawtok.notiyeah.data.NotificationEntity
import com.drawtok.notiyeah.data.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(private val repository: NotificationRepository) : ViewModel() {

    private val _notifications = MutableStateFlow<List<NotificationEntity>>(emptyList())
    val notifications: StateFlow<List<NotificationEntity>> = _notifications

    init {
        fetchNotifications()
    }

    private fun fetchNotifications() {
        _notifications.value = emptyList()
        viewModelScope.launch {

        }
    }

    fun insertNotification(notification: NotificationEntity) {
        viewModelScope.launch {
        }
    }

}