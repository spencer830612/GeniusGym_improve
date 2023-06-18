package com.example.geniusgym.coach.calendarMemberListDetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.coach.calendarMemberListDetail.model.SportBigItem
import com.example.geniusgym.coach.calendarMemberListDetail.model.SportSmallItem

class CoCalenderMemberRecordOxSmallViewModel : ViewModel() {
    val items: MutableLiveData<List<SportSmallItem>> by lazy { MutableLiveData<List<SportSmallItem>>() }
    val item: MutableLiveData<SportSmallItem> by lazy { MutableLiveData<SportSmallItem>() }
    private var itemList = listOf<SportSmallItem>()
}