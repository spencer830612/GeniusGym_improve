package com.example.geniusgym.coach.calendarMemberListDetail.model
import java.io.Serializable
class SportBigItem(
    var sb_id: String,
    var sb_name: String
)

class SportSmallItem(
    var sb_id: String,
    var sc_id: String,
    var sc_name: String
):Serializable