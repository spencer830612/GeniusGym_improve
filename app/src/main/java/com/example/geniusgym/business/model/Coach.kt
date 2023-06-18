package com.example.geniusgym.business.model

import java.sql.Timestamp

data class Coach(
    var c_id: String? = null,
    var bh_name: String? = null,
    var bh_id: Int? = null,
    var c_pwd: String? = null,
    var c_name: String? = null,
    var c_gen: Int? = null,
    var c_cell: String? = null,
    var c_twid: String? = null,
    var c_addr: String? = null,
    var c_start_date: Timestamp? = null,
    var c_add_time: Timestamp? = null,
    var c_modi_time: Timestamp? = null,
    var b_id: String? = null,
    var c_email: String? = null,
    var c_intro: String? = null,
    var c_picBase64: String? = null,
    var c_pic: Byte? = null,
    var c_sus: Boolean? = null
    ): java.io.Serializable

class testBuCoach(
    var c_id: String?,
    var bh_id: String?,    //原本是Int 要加判斷轉字串
    var c_pwd: String?,
    var c_name: String?,
    var c_gen: String?,     //原本是Int 要加判斷轉字串
    var c_cell: String?,
    var c_twid: String?,
    var c_addr: String?,
    var c_start_time: String?,
    var c_add_time: String?,
    var c_modi_time: String?,
    var b_id: String?,
    var c_email: String?,
    var c_intro: String?,
    var c_pic: Int?,
    var c_sus: Boolean?): java.io.Serializable