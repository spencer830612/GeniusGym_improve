package com.example.geniusgym.business.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.business.model.Coach
import java.text.SimpleDateFormat
import java.util.*

class BuCoachDataAddViewModel : ViewModel() {
    val coach: MutableLiveData<Coach> by lazy { MutableLiveData<Coach>(Coach()) }

    fun genToString():String? {
        if (coach.value?.c_gen == 0){
            return "女"
        }
        if (coach.value?.c_gen == 1){
            return "男"
        }
        return null
    }

    fun timeToString():String? {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return coach.value?.c_start_date?.let { format.format(it) }
    }
}