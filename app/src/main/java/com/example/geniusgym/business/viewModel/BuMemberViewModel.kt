package com.example.geniusgym.business.viewModel

import android.app.AlertDialog
import android.text.Editable
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.geniusgym.business.model.Member
import com.example.geniusgym.business.model.testBuMember
import com.example.geniusgym.sharedata.MeShareData
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import tw.idv.william.androidwebserver.core.service.requestTask
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class BuMemberViewModel: ViewModel() {
    val url = MeShareData.javaWebUrl + "buMember"
   // val url = "http://10.0.2.2:8080/geninusgym_bg/buMember"

    val member: MutableLiveData<Member> by lazy { MutableLiveData<Member>() }
    val list: MutableLiveData<List<Member>> by lazy { MutableLiveData<List<Member>>() }


    fun genToString():String? {
        if (member.value?.m_gen == 0){
           return "女"
        }
        if (member.value?.m_gen == 1){
            return "男"
        }
        return null
    }

    fun timeToString():String? {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return member.value?.m_ed_date?.let { format.format(it) }
    }

//    fun toFormattedString(): String? {
//        var datetime = member.value?.m_ed_time
//        datetime = SimpleDateFormat("yyyy-MM-dd HH:mm")
//        return datetime
//    }
}