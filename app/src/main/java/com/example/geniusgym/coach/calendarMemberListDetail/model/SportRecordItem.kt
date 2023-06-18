package com.example.geniusgym.coach.calendarMemberListDetail.model

class SportRecordItem(
    var sd_bigid: String?,
    var m_id: String?,         // 會員 ID
    var sc_id: String,         // 運動 ID
    var sc_weigt: Float?, // 重量
    var sc_freq: Int?,   // 次數
    var sc_set:Int?,
    var sd_record_time:String?,
    var c_id: String = "",      // 教練 ID
    var m_name: String?,         // 會員名字
    var sc_name: String?,        // 運動名字
){
  //  constructor()
}

class SportRecordBigItem(
    var data: MutableList<SportRecordItem>,
    var m_id: String?,
    var name: String?,
    var time: String?,
    var count: String?
):java.io.Serializable