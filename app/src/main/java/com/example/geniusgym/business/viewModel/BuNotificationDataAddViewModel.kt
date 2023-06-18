package com.example.geniusgym.business.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.business.model.Member
import com.example.geniusgym.business.model.Notify

class BuNotificationDataAddViewModel : ViewModel() {
    val notify: MutableLiveData<Notify> by lazy { MutableLiveData<Notify>(Notify()) }

//    val branches: MutableLiveData<List<String>>
//            by lazy { MutableLiveData<List<String>>(listOf("分店A", "分店B", "分店C", "分店D")) }
}