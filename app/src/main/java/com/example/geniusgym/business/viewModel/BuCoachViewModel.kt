package com.example.geniusgym.business.viewModel

import android.app.AlertDialog
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.business.model.Coach
import com.google.gson.JsonObject
import tw.idv.william.androidwebserver.core.service.requestTask
import java.text.SimpleDateFormat
import java.util.*

class BuCoachViewModel: ViewModel() {
    val branch: MutableLiveData<List<String>> by lazy { MutableLiveData<List<String>>() }
    val coach: MutableLiveData<Coach> by lazy { MutableLiveData<Coach>() }

    val url = "http://10.0.2.2:8080/geninusgym_bg/buCoach"

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

    fun Suspend(view: View){
        if (coach?.value!!.c_sus == true) {
            AlertDialog.Builder(view.context)
                .setMessage("確定將此用戶停權?")
                .setPositiveButton("是") { _, _ ->
                    coach?.value.run {
                        requestTask<JsonObject>(url, "DELETE", coach.value)
                        println(coach?.value)
                    }
                }
                .setCancelable(true)
                .show()
        }else{AlertDialog.Builder(view.context)
            .setMessage("確定將此用戶解除停權?")
            .setPositiveButton("是") { _, _ ->
                coach?.value.run {
                    requestTask<JsonObject>(url, "DELETE", coach?.value)
                    println(coach?.value)
                }
            }
            .setCancelable(true)
            .show()}
        true
    }
//    private fun loadBranches() {
//        viewModelScope.launch {
//            branch.value = Branches()
//        }
//    }
//
//    private suspend fun Branches(): List<String> {
//        val url = "http://10.0.2.2:8080/WebRequest_Web/SearchServlet"
//        val jsonObject = JsonObject()
//        jsonObject.addProperty("action", "type")
//        val jsonIn: String = WebRequest().httpPost(url, jsonObject.toString())
//        //匿名內部類別TypeToken
//        val listType = object : TypeToken<List<String?>?>() {}.type
//        return Gson().fromJson(jsonIn, listType)
//    }


}