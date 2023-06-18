package com.example.geniusgym.member.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.member.model.WorkoutMainItem

class MeTrainingMuscleViewModel : ViewModel() {
    val items: MutableLiveData<List<WorkoutMainItem>> by lazy { MutableLiveData<List<WorkoutMainItem>>() }
    val item: MutableLiveData<WorkoutMainItem> by lazy { MutableLiveData<WorkoutMainItem>() }

    init {
        load()
    }

    private fun load() {
        val strengthList: MutableList<WorkoutMainItem> = mutableListOf<WorkoutMainItem>()

        strengthList.add(WorkoutMainItem("2", "肩"))
        strengthList.add(WorkoutMainItem("3", "胸"))
        strengthList.add(WorkoutMainItem("4", "背"))
        strengthList.add(WorkoutMainItem("5", "腿"))
        strengthList.add(WorkoutMainItem("6", "臀"))
        strengthList.add(WorkoutMainItem("7", "腹"))
        strengthList.add(WorkoutMainItem("8", "核心"))
        strengthList.add(WorkoutMainItem("9", "二三頭"))

        this.items.value = strengthList
    }

}