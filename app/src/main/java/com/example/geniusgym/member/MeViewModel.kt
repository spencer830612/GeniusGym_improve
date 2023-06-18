package com.example.geniusgym.member

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.member.model.TrainingMemberItem
import com.example.geniusgym.member.model.WorkoutMainItem
import com.example.geniusgym.member.model.WorkoutSmallItem

class MeViewModel: ViewModel() {
    val name: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val member: MutableLiveData<TrainingMemberItem> by lazy { MutableLiveData<TrainingMemberItem>() }
    val WorkoutSmallItem: MutableLiveData<List<WorkoutSmallItem>> by lazy { MutableLiveData<List<WorkoutSmallItem>>() }
    val WorkoutMainItem: MutableLiveData<List<WorkoutMainItem>> by lazy { MutableLiveData<List<WorkoutMainItem>>() }
}