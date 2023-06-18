package com.example.geniusgym.member.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.member.model.WorkoutSmallItem

class MeTrainingAerobicSmallViewModel: ViewModel() {
    val items: MutableLiveData<List<WorkoutSmallItem>> by lazy { MutableLiveData<List<WorkoutSmallItem>>() }
    val item: MutableLiveData<WorkoutSmallItem> by lazy { MutableLiveData<WorkoutSmallItem>() }
    private var itemList = listOf<WorkoutSmallItem>()
}