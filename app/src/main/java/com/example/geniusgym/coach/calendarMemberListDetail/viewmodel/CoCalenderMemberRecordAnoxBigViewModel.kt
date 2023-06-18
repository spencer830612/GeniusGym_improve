package com.example.geniusgym.coach.calendarMemberListDetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.coach.calendarMemberListDetail.model.SportBigItem

class CoCalenderMemberRecordAnoxBigViewModel : ViewModel(){
    val items: MutableLiveData<List<SportBigItem>> by lazy { MutableLiveData<List<SportBigItem>>() }
    val item: MutableLiveData<SportBigItem> by lazy { MutableLiveData<SportBigItem>() }
    init {
        load()
    }
    private fun load(){
        val AnoxList : MutableList<SportBigItem> = mutableListOf<SportBigItem>()

        AnoxList.add(SportBigItem("2", "肩"))
        AnoxList.add(SportBigItem("3","胸"))
        AnoxList.add(SportBigItem("4","背"))
        AnoxList.add(SportBigItem("5", "腿"))

        this.items.value = AnoxList
    }
}