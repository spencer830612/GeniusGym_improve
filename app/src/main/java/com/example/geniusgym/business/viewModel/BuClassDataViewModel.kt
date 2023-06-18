package com.example.geniusgym.business.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.business.model.Class_Info
import com.example.geniusgym.business.model.Coach
import com.example.geniusgym.sharedata.MeShareData
import com.google.gson.reflect.TypeToken
import tw.idv.william.androidwebserver.core.service.requestTask

class BuClassDataViewModel : ViewModel() {
    // 原始課程列表
    private var BuClassList = mutableListOf<Class_Info>()
    // 受監控的LiveData，一旦指派新值就會更新教練列表畫面
    val classes: MutableLiveData<List<Class_Info>> by lazy { MutableLiveData<List<Class_Info>>() }
    val url = MeShareData.javaWebUrl + "buClass"

    fun inti(){
        val type = object : TypeToken<List<Class_Info>>() {}.type
        classes.value = requestTask<List<Class_Info>>(url, respBodyType = type)
        println(classes.value?.get(0))
    }
}