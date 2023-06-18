package com.example.geniusgym.member.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.member.model.WorkoutSecondItem

class MeTrainingRecordViewModel : ViewModel() {
    val recordItems: MutableLiveData<MutableList<WorkoutSecondItem>> by lazy { MutableLiveData<MutableList<WorkoutSecondItem>>() }
    val recordItem: MutableLiveData<WorkoutSecondItem> by lazy { MutableLiveData<WorkoutSecondItem>() }
    val errorMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val name: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val sportName: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val weight: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val freq: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val textDate: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val setID: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    var sc_id: String = ""
    var m_id: String = ""

    init {
        load()
    }

    private fun load() {
        recordItems.value = arrayListOf()
    }

    fun onClick() {
        val item = 1
    }
}