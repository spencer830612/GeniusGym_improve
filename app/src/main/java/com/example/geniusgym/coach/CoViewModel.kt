package com.example.geniusgym.coach

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geniusgym.coach.calendarMemberList.model.MemberItem
import com.example.geniusgym.coach.calendarMemberListDetail.model.CoachItem
import com.example.geniusgym.coach.calendarMemberListDetail.model.SportBigItem
import com.example.geniusgym.coach.calendarMemberListDetail.model.SportSmallItem
import com.example.geniusgym.util.WebRequestSpencer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CoViewModel : ViewModel() {
    val name: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val member: MutableLiveData<MemberItem> by lazy { MutableLiveData<MemberItem>() }
    val sportSmallItems: MutableLiveData<List<SportSmallItem>> by lazy { MutableLiveData<List<SportSmallItem>>() }
    val sportBigItems: MutableLiveData<List<SportBigItem>> by lazy { MutableLiveData<List<SportBigItem>>() }
    val coach: MutableLiveData<CoachItem> by lazy { MutableLiveData<CoachItem>() }

    init {
        loadFake()
    }
    fun loadSportFromPreference(coActivity: CoActivity) {
        val gson = GsonBuilder().create()
        val smallItemType = object : TypeToken<List<SportSmallItem>?>() {}.type
        val bigItemType = object : TypeToken<List<SportBigItem>?>() {}.type
        val preferences = coActivity.getPreferences(Context.MODE_PRIVATE)
        val smallJson = preferences.getString("sportSmallItems", "")
        preferences.getString("sportBigItems", "")
        sportSmallItems?.value = gson.fromJson(smallJson, smallItemType)
        sportBigItems?.value = gson.fromJson(smallJson, bigItemType)
    }

    fun loadSportSmallItem() {
        viewModelScope.launch {
            val deffered1 = async { sportSmallItemImport() }
            sportSmallItems?.value = deffered1.await()
        }
    }

    fun loadSportBigItem() {
        viewModelScope.launch {
            val deffered2 = async{sportBigItemImport()}
            sportBigItems?.value = deffered2.await()
        }
    }

    private suspend fun sportSmallItemImport(): List<SportSmallItem> {
        val jsonIn: String = WebRequestSpencer().httpPost("GetSportCat", "start")
        val type = object : TypeToken<List<SportSmallItem>?>() {}.type
        println("JSONPOSTSMALL: $jsonIn")
        return Gson().fromJson(jsonIn, type)
    }

    private suspend fun sportBigItemImport(): List<SportBigItem> {
        val jsonIn: String = WebRequestSpencer().httpGet("GetSportCat")
        val type = object : TypeToken<List<SportBigItem>?>() {}.type
        return Gson().fromJson(jsonIn,type)
    }

    private fun loadFake() {
        coach?.value =
            CoachItem(
                c_name = "桃園 hawk",
                c_cell = "0912345678",
                c_id = "C87632",
                c_start_date = "2023/06/15"
            )
    }
}