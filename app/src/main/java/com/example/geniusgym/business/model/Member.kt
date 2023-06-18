package com.example.geniusgym.business.model

import java.sql.Timestamp

data class Member(
    var m_id: String? = null,
    var m_token: String? = null,
    var m_pwd: String? = null,
    var m_name: String? = null,
    var m_gen: Int? = null,
    var m_cell: String? = null,
    var m_twid: String? = null,
    var m_addr: String? = null,
    var m_add_time: Timestamp? = null,
    var m_ed_date: Timestamp? = null,
    var m_modi_time: Timestamp? = null,
    var b_id: String? = null,
    var m_email: String? = null,
    var m_picBase64: String? = null,
    var m_pic: Byte? = null,
    var m_sus: Boolean? = null
    ): java.io.Serializable



class testBuMember(
    var m_id: String?,
    var m_pwd: String?,
    var m_name: String?,
    var m_gen: String?,   //原本是Int 要加判斷轉字串
    var m_cell: String?,
    var m_twid: String?,
    var m_addr: String?,
    var m_add_time: String?,
    var m_ed_time: String?,
    var m_modi_time: String?,
    var b_id: String?,
    var m_email: String?,
    var m_pic: Int?,
    var m_sus: Boolean?): java.io.Serializable