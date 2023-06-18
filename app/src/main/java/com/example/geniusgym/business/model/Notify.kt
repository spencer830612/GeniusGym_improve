package com.example.geniusgym.business.model

import java.sql.Timestamp

class Notify (
    var nt_id: Int? = null,
    var b_id: String? = null,
    var nt_send_time: Timestamp? = null,
    var bh_id: Int? = null,
    var nt_text: String? = null,
    var ntpicBase64: String?= null,
    var nt_pic: Byte? = null,
    ): java.io.Serializable