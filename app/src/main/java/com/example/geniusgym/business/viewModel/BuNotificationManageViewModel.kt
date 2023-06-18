package com.example.geniusgym.business.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.business.model.Coach
import com.example.geniusgym.business.model.Notify
import com.example.geniusgym.sharedata.MeShareData
import com.google.gson.reflect.TypeToken
import tw.idv.william.androidwebserver.core.service.requestTask

/**
 * 通知列表資料處理
 */
class BuNotificationManageViewModel : ViewModel() {
  //  val url = "http://10.0.2.2:8080/geninusgym_bg/buCoach"
    val url = MeShareData.javaWebUrl + "buCoach"
    // 原始公告列表
    private var BuNotiList = mutableListOf<Notify>()
    // 受監控的LiveData，一旦指派新值就會更新教練列表畫面
    val notifies: MutableLiveData<List<Notify>> by lazy { MutableLiveData<List<Notify>>() }

    fun inti(){
        val type = object : TypeToken<List<Notify>>() {}.type
        notifies.value = requestTask<List<Notify>>(url, respBodyType = type)
        println(notifies.value?.get(0))
    }
}