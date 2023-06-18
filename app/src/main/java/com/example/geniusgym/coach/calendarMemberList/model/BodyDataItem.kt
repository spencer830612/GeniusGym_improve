package com.example.geniusgym.coach.calendarMemberList.model
import java.io.Serializable
class BodyDataItem (
    var bodyDataId : String,
    var memberId : String,
    var bodyDataHeight : Float,
    var bodyDataFat: Float,
    var bodyDataAddTime: String,
    var bodyDataUpdateTime: String
):Serializable