package com.example.geniusgym.member.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.member.model.WorkoutMainItem

class MeTrainingAerobicMainViewModel: ViewModel() {
    val items: MutableLiveData<List<WorkoutMainItem>> by lazy { MutableLiveData<List<WorkoutMainItem>>() }
    val item: MutableLiveData<WorkoutMainItem> by lazy { MutableLiveData<WorkoutMainItem>() }
}