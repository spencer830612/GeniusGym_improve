package com.example.geniusgym.member.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.member.model.ClassInfo

class MeShopCartViewModel : ViewModel() {

    val classInfos : MutableLiveData<MutableList<ClassInfo>> by lazy { MutableLiveData() }


}