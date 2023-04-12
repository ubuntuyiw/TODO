package com.ubuntuyouiwe.todo.presentation.component.notification

import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.ubuntuyouiwe.todo.data.dto.remote.NotificationOption
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class NotificationSharedViewModel @Inject constructor(

) : ViewModel() {

    var notificationState = MutableStateFlow<NotificationOption?>(null)
        private set

    var timestamp = MutableStateFlow<Timestamp?>(null)
        private set






}