package com.example.geniusgym.member.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.member.model.ClassInfo

class MeRecycShopViewModel : ViewModel() {

//    dataBinding綁定layout上的所有物件
    val coachName : MutableLiveData<String> by lazy { MutableLiveData("我") }
    val startToEnd : MutableLiveData<String> by lazy { MutableLiveData("是") }
    val point : MutableLiveData<String> by lazy { MutableLiveData("誰") }
    val lessondate : MutableLiveData<String> by lazy { MutableLiveData("?") }
    val lessonName : MutableLiveData<String> by lazy { MutableLiveData() }
    val lessonKind : MutableLiveData<String> by lazy { MutableLiveData() }

}