package com.example.geniusgym.coach.calendarMemberListDetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.geniusgym.coach.calendarMemberListDetail.model.SportRecordItem
import com.example.geniusgym.util.WebRequestSpencer
import com.google.gson.GsonBuilder

class CoCalenderMemberRecordAfterViewModel : ViewModel() {
    val recordItems: MutableLiveData<MutableList<SportRecordItem>> by lazy { MutableLiveData<MutableList<SportRecordItem>>() }
    val recordItem: MutableLiveData<SportRecordItem> by lazy { MutableLiveData<SportRecordItem>() }
    val errorMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val name: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val sportName: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val weight: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val freq: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val textDate: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val setID: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    var sc_id: String = ""
    var m_id: String = ""
    private val recordList = mutableListOf<SportRecordItem>()


    fun onClick() {

        if (!stringCheck(weight?.value) || !stringCheck(freq?.value)) {
            errorMessage.value = "請檢查重量或次數是否有正確輸入"
            return
        }
        errorMessage.value = ""
        val item = SportRecordItem(
            m_id = this.m_id,
            m_name = name?.value,
            sc_id = this.sc_id,
            sc_name = sportName?.value,
            sc_weigt = weight?.value?.toFloat(),
            sc_freq = freq?.value?.toInt(),
            c_id = "RRRRR",
            sd_record_time = textDate?.value,
            sd_bigid = setID?.value,
            sc_set = if (recordItems.value == null) {
                1
            } else {
                recordItems.value!!.size + 1
            }

        )
        recordList.add(item)
        recordItems.value = recordList
    }

    private fun stringCheck(string: String?): Boolean {
        // 如果輸出為 true ，代表字串通過檢查
        return if (string == null) {
            false
        } else {
            string.trim().isNotEmpty() &&
                    string.matches(Regex("[1-9]\\d*"))
        }
    }
    suspend fun sportDataUpload():String{
        val gson = GsonBuilder().create()
        val jsonListSportRecordItem = gson.toJson(recordItems?.value)
        println(jsonListSportRecordItem)
        val jsonIn: String = WebRequestSpencer().httpPost("GetSportData",jsonListSportRecordItem.toString())
        return jsonIn
    }
}