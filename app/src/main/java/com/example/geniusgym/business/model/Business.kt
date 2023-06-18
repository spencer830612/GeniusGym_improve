package com.example.geniusgym.business.model

import java.sql.Timestamp

class Business(
    var b_id: String? = null,
    var bh_id: Int? = null,
    var bh_name: String? = null,
    var b_pwd: String? = null,
    var b_name: String? = null,
    var b_gen: Int? = null,
    var b_cell: String? = null,
    var b_twid: String? = null,
    var b_addr: String? = null,
    var b_start_date: Timestamp? = null,
    var b_add_time: Timestamp? = null,
    var b_modi_time: Timestamp? = null,
    var b_modi_id: String? = null,
    var b_email: String? = null,
    var b_intro: String? = null,
    var b_picBase64: String? = null,
    var b_pic: Byte? = null,
    var b_sus: Boolean? = null
    ): java.io.Serializable

class testBuBusiness(
    var b_id: String?,
    var bh_id: String?,   //原本是Int 要加判斷轉字串
    var b_pwd: String?,
    var b_name: String?,
    var b_gen: String?,   //原本是Int 要加判斷轉字串
    var b_cell: String?,
    var b_twid: String?,
    var b_addr: String?,
    var b_start_date: String?,
    var b_add_time: String?,
    var b_modi_time: String?,
    var b_modi_id: String?,
    var b_email: String?,
    var b_pic: Int?,
    var b_sus: Boolean?
    ): java.io.Serializable