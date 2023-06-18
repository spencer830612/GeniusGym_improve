package com.example.geniusgym.business.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.business.model.Business
import java.text.SimpleDateFormat
import java.util.*

class BuBusinessDataAddViewModel : ViewModel() {
    val buz: MutableLiveData<Business> by lazy { MutableLiveData<Business>(Business()) }

    fun genToString():String? {
        if (buz.value?.b_gen == 0){
            return "女"
        }
        if (buz.value?.b_gen == 1){
            return "男"
        }
        return null
    }

    fun timeToString():String? {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return buz.value?.b_start_date?.let { format.format(it) }
    }
}