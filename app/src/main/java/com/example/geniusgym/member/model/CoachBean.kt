package com.example.geniusgym.member.model

import android.graphics.drawable.Drawable

data class CoachBean(
    val c_name: String = "伯啟教練",
    val c_pic: ByteArray? = null,
    val c_gen:Int = 2,
    val c_intro: String = "大家好，我是伯啟，我的專業是教大家如何用螺旋突刺，讓大家在家裡可以把另一半刺的不要不要的"
) : java.io.Serializable