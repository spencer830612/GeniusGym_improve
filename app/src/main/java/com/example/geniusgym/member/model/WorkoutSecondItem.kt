package com.example.geniusgym.member.model
import java.io.Serializable

class WorkoutSecondItem (
    var sd_bigId: String?,
    var m_id: String?,
    var sc_id: String,
    var sc_weight: Float?,
    var sc_freq: Int?,
    var sc_set: Int?,
    var sd_record_time: String?,
    var m_name: String?,
    var sc_name: String?
    )

class WorkoutBigItem (
    var data:MutableList<WorkoutSecondItem>,
    var m_id: String?,
    var name: String?,
    var time: String?,
    var count: String?
    ):Serializable