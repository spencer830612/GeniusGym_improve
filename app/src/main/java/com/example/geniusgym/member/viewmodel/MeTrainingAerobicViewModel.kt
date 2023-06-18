package com.example.geniusgym.member.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.member.model.WorkoutSecondItem
import com.example.geniusgym.member.model.WorkoutSmallItem

class MeTrainingAerobicViewModel : ViewModel() {
    val items: MutableLiveData<List<WorkoutSmallItem>> by lazy { MutableLiveData<List<WorkoutSmallItem>>() }
    val item: MutableLiveData<WorkoutSmallItem> by lazy { MutableLiveData<WorkoutSmallItem>() }

    init {
        load()
    }

    private fun load() {
        val items = mutableListOf<WorkoutSmallItem>()

        items.add(WorkoutSmallItem("1", "1", "飛輪"))
        items.add(WorkoutSmallItem("1", "2", "靜態"))
        items.add(WorkoutSmallItem("1", "3", "心肺訓練"))
        items.add(WorkoutSmallItem("1", "4", "跑步"))
        items.add(WorkoutSmallItem("1", "5", "舞蹈"))
        items.add(WorkoutSmallItem("1", "6", "騎腳踏車"))
        items.add(WorkoutSmallItem("1", "7", "游泳"))
        items.add(WorkoutSmallItem("1", "8", "球類運動"))

        this.items.value = items
    }
}