package com.example.geniusgym.coach.calendarMemberList.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class CoCalendarViewModel : ViewModel() {
    val textDate: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val Date: MutableLiveData<LocalDate> by lazy { MutableLiveData<LocalDate>() }
}