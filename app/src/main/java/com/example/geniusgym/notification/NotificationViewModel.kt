package com.example.geniusgym.notification

import android.icu.text.SimpleDateFormat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.notification.model.Notification
import java.sql.Timestamp

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NotificationViewModel : ViewModel() {
    val item: MutableLiveData<Notification> by lazy { MutableLiveData<Notification>() }
    val items: MutableLiveData<List<Notification>> by lazy { MutableLiveData<List<Notification>>() }

    init {
        load()
    }
    private fun load() {
        val notificationList: MutableList<Notification> = mutableListOf<Notification>()
        val formatter = DateTimeFormatter.BASIC_ISO_DATE
        notificationList.add(Notification(1,"m123456", 2, "4/15下午兩點在緯育分店A503有課，親愛的會員如需請假請事前告知", Timestamp.valueOf("2023-06-16 00:00:00")))

        this.items.value = notificationList
    }
}