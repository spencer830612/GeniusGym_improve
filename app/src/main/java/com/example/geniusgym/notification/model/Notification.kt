package com.example.geniusgym.notification.model

import java.io.Serializable
import java.sql.Timestamp

class Notification (
    var nt_id: Int?,
    var b_id: String?,
    var bh_id: Int?,
    var nt_text: String?,
    var nt_send_time: Timestamp?,


        ):Serializable
// 我記得我們的通知物件裡面長這樣嗎?等喔