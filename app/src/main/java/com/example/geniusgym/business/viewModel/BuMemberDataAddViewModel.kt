package com.example.geniusgym.business.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.business.model.Member
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


import java.util.*


class BuMemberDataAddViewModel : ViewModel() {
    val member: MutableLiveData<Member> by lazy { MutableLiveData<Member>(Member()) }


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


}