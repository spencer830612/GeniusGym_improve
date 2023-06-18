package com.example.geniusgym.member.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.member.model.TrainingItem
import java.time.LocalDate

class MeTrainingCalendarViewModel : ViewModel() {
    val workoutItem: MutableLiveData<TrainingItem> by lazy { MutableLiveData<TrainingItem>() }
    val trainingTextDate: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val trainingDate: MutableLiveData<LocalDate> by lazy { MutableLiveData<LocalDate>() }
    val trainingItems: MutableLiveData<List<TrainingItem>> by lazy { MutableLiveData<List<TrainingItem>>() }

    private var trainingItemList = listOf<TrainingItem>()

    fun trainingSearch(input: String?) {
        val trainingSearchList = if (input == null || input.isEmpty()) {
            trainingItemList
        } else {
            trainingItemList.filter { item ->
                searchItem(item, input.trim())
            }
        }
        trainingItems.value = trainingSearchList
    }

    private fun searchItem(item: TrainingItem, searchText: String): Boolean {
        return item.workoutUpdateTime.contains(searchText, ignoreCase = false)
    }
}