package com.example.geniusgym.member.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalTime

class MeHomeViewModel : ViewModel() {

    val count : MutableLiveData<Int> by lazy { MutableLiveData() }


}