package com.example.geniusgym.coach.calendarMemberListDetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.coach.CoActivity
import com.example.geniusgym.coach.calendarMemberListDetail.model.SportSmallItem

class CoCalenderMemberRecordOxViewModel : ViewModel() {
    val items: MutableLiveData<List<SportSmallItem>> by lazy { MutableLiveData<List<SportSmallItem>>() }
    val item: MutableLiveData<SportSmallItem> by lazy { MutableLiveData<SportSmallItem>() }


    fun load(coActivity: CoActivity) {
        /*val items = mutableListOf<SportSmallItem>()

        items.add(SportSmallItem("1", "1", "飛輪"))
        items.add(SportSmallItem("1", "2", "靜態"))
        items.add(SportSmallItem("1", "3", "心肺訓練"))
        items.add(SportSmallItem("1", "4", "跑步"))
        items.add(SportSmallItem("1", "5", "舞蹈"))
        this.items.value = items */
        val oxId = coActivity.binding.viewModel?.sportBigItems?.value?.get(0)?.sb_id.toString()

        coActivity.binding.viewModel?.sportSmallItems?.value?.let {
            val items = it
            it[0].sb_id
            this.items.value = items.filter { item ->
                println(item.sb_id)
                item.sb_id.contains(oxId)
            }
        }

    }
}