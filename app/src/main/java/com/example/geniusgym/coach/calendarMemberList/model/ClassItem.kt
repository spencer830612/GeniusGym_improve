package com.example.geniusgym.coach.calendarMemberList.model
import java.io.Serializable
import java.sql.Timestamp

class ClassItem (
    var ci_id: String,
    var ci_start_time: String,
    var ci_ed_time: String,
    var ci_date: String,
    var ci_name: String
):Serializable