package com.example.geniusgym.business.viewModel

import android.app.AlertDialog
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.business.model.Member
import com.example.geniusgym.business.model.Notify
import com.google.gson.JsonObject
import tw.idv.william.androidwebserver.core.service.requestTask

class BuNotificationDetailViewModel : ViewModel() {
//  val url = "http://10.0.2.2:8080/geninusgym_bg/NotifyController/1"

    val notify: MutableLiveData<Notify> by lazy { MutableLiveData<Notify>() }

//    fun Hide(view: View){
//        if (notify?.value!!.c_sus == true) {
//            AlertDialog.Builder(view.context)
//                .setMessage("確定將此用戶停權?")
//                .setPositiveButton("是") { _, _ ->
//                    coach?.value.run {
//                        requestTask<JsonObject>(url, "DELETE", coach.value)
//                        println(coach?.value)
//                    }
//                }
//                .setCancelable(true)
//                .show()
//        }else{
//            AlertDialog.Builder(view.context)
//            .setMessage("確定將此用戶解除停權?")
//            .setPositiveButton("是") { _, _ ->
//                coach?.value.run {
//                    requestTask<JsonObject>(url, "DELETE", coach?.value)
//                    println(coach?.value)
//                }
//            }
//            .setCancelable(true)
//            .show()}
//        true
//    }

}